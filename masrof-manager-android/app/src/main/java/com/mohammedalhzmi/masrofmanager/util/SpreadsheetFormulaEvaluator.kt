package com.mohammedalhzmi.masrofmanager.util

import java.util.Locale
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.abs

/** A small, explicitly scoped formula engine for the first offline spreadsheet milestone. */
object SpreadsheetFormulaEvaluator {
    fun evaluate(formula: String, cells: Map<String, String>): String {
        if (!formula.trim().startsWith("=")) return formula
        return try {
            val normalized = normalizeDigits(formula.trim().drop(1))
            val parser = Parser(normalized, cells, linkedSetOf())
            val result = parser.expression()
            if (!parser.finished()) "#VALUE!" else format(result)
        } catch (_: DivideByZero) {
            "#DIV/0!"
        } catch (_: Throwable) {
            "#VALUE!"
        }
    }

    private fun format(value: Double): String {
        if (!value.isFinite()) return "#VALUE!"
        val rounded = if (abs(value - value.toLong().toDouble()) < 1e-10) value.toLong().toString()
        else String.format(Locale.US, "%.8f", value).trimEnd('0').trimEnd('.')
        return rounded
    }

    private fun normalizeDigits(value: String): String = value
        .replace('٠', '0').replace('١', '1').replace('٢', '2').replace('٣', '3')
        .replace('٤', '4').replace('٥', '5').replace('٦', '6').replace('٧', '7')
        .replace('٨', '8').replace('٩', '9')
        .replace('۰', '0').replace('۱', '1').replace('۲', '2').replace('۳', '3')
        .replace('۴', '4').replace('۵', '5').replace('۶', '6').replace('۷', '7')
        .replace('۸', '8').replace('۹', '9').replace('٫', '.')

    private class DivideByZero : RuntimeException()

    private class Parser(
        private val input: String,
        private val cells: Map<String, String>,
        private val resolving: MutableSet<String>
    ) {
        private var index = 0

        fun finished(): Boolean { skipSpaces(); return index == input.length }

        fun expression(): Double {
            var value = term()
            while (true) {
                skipSpaces()
                value = when {
                    take('+') -> value + term()
                    take('-') -> value - term()
                    else -> return value
                }
            }
        }

        private fun term(): Double {
            var value = unary()
            while (true) {
                skipSpaces()
                value = when {
                    take('*') -> value * unary()
                    take('/') -> {
                        val divisor = unary()
                        if (divisor == 0.0) throw DivideByZero()
                        value / divisor
                    }
                    else -> return value
                }
            }
        }

        private fun unary(): Double {
            skipSpaces()
            return when {
                take('+') -> unary()
                take('-') -> -unary()
                else -> primary()
            }
        }

        private fun primary(): Double {
            skipSpaces()
            if (take('(')) {
                val value = expression()
                expect(')')
                return value
            }
            if (index >= input.length) error("Missing operand")
            if (input[index].isDigit() || input[index] == '.') return number()
            if (input[index].isLetter()) {
                val identifier = identifier()
                skipSpaces()
                if (take('(')) return function(identifier.uppercase(Locale.ROOT))
                if (!CELL.matches(identifier.uppercase(Locale.ROOT))) error("Unknown name")
                return cellValue(identifier)
            }
            error("Unexpected token")
        }

        private fun function(name: String): Double {
            val numbers = mutableListOf<Double>()
            skipSpaces()
            if (!take(')')) {
                while (true) {
                    skipSpaces()
                    val start = index
                    if (isCellReferenceAhead()) {
                        val first = identifier().uppercase(Locale.ROOT)
                        skipSpaces()
                        if (take(':')) {
                            val last = identifier().uppercase(Locale.ROOT)
                            if (!CELL.matches(first) || !CELL.matches(last)) error("Invalid range")
                            numbers += rangeValues(first, last)
                        } else {
                            index = start
                            numbers += expression()
                        }
                    } else {
                        numbers += expression()
                    }
                    skipSpaces()
                    if (take(')')) break
                    if (!(take(',') || take(';'))) error("Expected argument separator")
                }
            }
            return when (name) {
                "SUM" -> numbers.sum()
                "AVERAGE" -> if (numbers.isEmpty()) 0.0 else numbers.average()
                "MIN" -> numbers.minOrNull() ?: 0.0
                "MAX" -> numbers.maxOrNull() ?: 0.0
                "PRODUCT" -> numbers.fold(1.0) { product, value -> product * value }
                "ABS" -> { require(numbers.size == 1); abs(numbers.single()) }
                "ROUND" -> {
                    require(numbers.size in 1..2)
                    val digits = numbers.getOrElse(1) { 0.0 }.toInt().coerceIn(-8, 8)
                    BigDecimal.valueOf(numbers.first()).setScale(digits, RoundingMode.HALF_UP).toDouble()
                }
                else -> error("Unsupported function")
            }
        }

        private fun number(): Double {
            val start = index
            var dots = 0
            while (index < input.length && (input[index].isDigit() || input[index] == '.')) {
                if (input[index] == '.') dots++
                if (dots > 1) error("Invalid number")
                index++
            }
            return input.substring(start, index).toDouble()
        }

        private fun identifier(): String {
            val start = index
            while (index < input.length && input[index].isLetterOrDigit()) index++
            if (start == index) error("Expected reference or function")
            return input.substring(start, index)
        }

        private fun isCellReferenceAhead(): Boolean {
            val match = Regex("[A-Za-z]+[0-9]+(?:\\s*:)?").find(input.substring(index)) ?: return false
            return match.range.first == 0
        }

        private fun cellValue(reference: String): Double {
            val ref = reference.uppercase(Locale.ROOT)
            if (!resolving.add(ref)) error("Circular reference")
            return try {
                val raw = cells[ref].orEmpty().trim()
                if (raw.startsWith("=")) {
                    val nested = Parser(raw.drop(1), cells, resolving)
                    val value = nested.expression()
                    if (!nested.finished()) error("Invalid referenced formula")
                    value
                } else normalizeDigits(raw).toDoubleOrNull() ?: 0.0
            } finally {
                resolving.remove(ref)
            }
        }

        private fun rangeValues(first: String, last: String): List<Double> {
            val (firstColumn, firstRow) = coordinates(first)
            val (lastColumn, lastRow) = coordinates(last)
            val minColumn = minOf(firstColumn, lastColumn)
            val maxColumn = maxOf(firstColumn, lastColumn)
            val minRow = minOf(firstRow, lastRow)
            val maxRow = maxOf(firstRow, lastRow)
            require((maxColumn - minColumn + 1) * (maxRow - minRow + 1) <= 10000) { "Range too large" }
            return buildList {
                for (row in minRow..maxRow) for (column in minColumn..maxColumn) {
                    add(cellValue("${columnName(column)}$row"))
                }
            }
        }

        private fun coordinates(reference: String): Pair<Int, Int> {
            val match = CELL.matchEntire(reference) ?: error("Invalid cell")
            val column = match.groupValues[1].uppercase(Locale.ROOT).fold(0) { total, char -> total * 26 + (char - 'A' + 1) }
            val row = match.groupValues[2].toInt()
            require(column in 1..16384 && row in 1..1048576)
            return column to row
        }

        private fun columnName(number: Int): String {
            var current = number
            val result = StringBuilder()
            while (current > 0) {
                val remainder = (current - 1) % 26
                result.append(('A'.code + remainder).toChar())
                current = (current - 1) / 26
            }
            return result.reverse().toString()
        }

        private fun skipSpaces() { while (index < input.length && input[index].isWhitespace()) index++ }
        private fun take(char: Char): Boolean { skipSpaces(); if (index < input.length && input[index] == char) { index++; return true }; return false }
        private fun expect(char: Char) { if (!take(char)) error("Expected $char") }

        companion object {
            private val CELL = Regex("([A-Z]+)([1-9][0-9]*)")
        }
    }
}
