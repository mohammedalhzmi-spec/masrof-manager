package com.mohammedalhzmi.masrofmanager.util

import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * Versioned, lossless payload for branch equipment and supply rows.
 * The records use the existing BOOK ledger type and Firestore sync path so this feature
 * does not require a Room migration or new production Firestore rules. The prefix makes
 * them distinguishable from ordinary document-book pages and legacy records.
 */
object BranchAssetData {
    private const val PREFIX = "MASROF_BRANCH_ASSET_V1|"
    private const val EQUIPMENT = "E"
    private const val SUPPLY = "S"

    data class Equipment(
        val name: String,
        val model: String,
        val condition: String,
        val administrativeNumber: String,
        val machineNumber: String,
        val chassisNumber: String,
        val yearObtained: String,
        val responsiblePerson: String
    )

    data class Supply(
        val day: String,
        val date: String,
        val name: String,
        val kind: String,
        val valueYER: String
    )

    enum class Kind { EQUIPMENT, SUPPLY }

    fun encode(value: Equipment): String = encodeFields(EQUIPMENT, listOf(
        value.name, value.model, value.condition, value.administrativeNumber,
        value.machineNumber, value.chassisNumber, value.yearObtained, value.responsiblePerson
    ))

    fun encode(value: Supply): String = encodeFields(SUPPLY, listOf(
        value.day, value.date, value.name, value.kind, value.valueYER
    ))

    fun decodeEquipment(document: Document): Equipment? {
        val fields = decodeFields(document.details, EQUIPMENT, 8) ?: return null
        return Equipment(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7])
    }

    fun decodeSupply(document: Document): Supply? {
        val fields = decodeFields(document.details, SUPPLY, 5) ?: return null
        return Supply(fields[0], fields[1], fields[2], fields[3], fields[4])
    }

    fun kind(document: Document): Kind? {
        if (document.type != DocumentType.BOOK) return null
        return when {
            decodeFields(document.details, EQUIPMENT, 8) != null -> Kind.EQUIPMENT
            decodeFields(document.details, SUPPLY, 5) != null -> Kind.SUPPLY
            else -> null
        }
    }

    fun isRecord(document: Document): Boolean = kind(document) != null

    private fun encodeFields(kind: String, values: List<String>): String =
        PREFIX + kind + "|" + values.joinToString("|") { URLEncoder.encode(it, StandardCharsets.UTF_8.name()) }

    private fun decodeFields(raw: String?, expectedKind: String, expectedCount: Int): List<String>? {
        val text = raw ?: return null
        if (!text.startsWith(PREFIX)) return null
        val fields = text.removePrefix(PREFIX).split('|')
        if (fields.size != expectedCount + 1 || fields.firstOrNull() != expectedKind) return null
        return fields.drop(1).map { value ->
            runCatching { URLDecoder.decode(value, StandardCharsets.UTF_8.name()) }.getOrElse { return null }
        }
    }
}
