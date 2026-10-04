package com.mohammedalhzmi.masrofmanager.ui

import android.graphics.Typeface
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.text.Editable
import android.text.Html
import android.text.Spannable
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.AbsoluteSizeSpan
import android.text.style.AlignmentSpan
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import com.example.R
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.util.LocalOfficeDocument
import com.mohammedalhzmi.masrofmanager.util.LocalOfficeDocumentStore
import com.mohammedalhzmi.masrofmanager.util.OfficeDocumentRecord
import com.mohammedalhzmi.masrofmanager.util.OfficeDocumentKind
import com.mohammedalhzmi.masrofmanager.util.SpreadsheetFormulaEvaluator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DateFormat
import java.util.Date

private data class ArabicFontChoice(val key: String, val label: String, val resourceId: Int)

private val ArabicFonts = listOf(
    ArabicFontChoice("cairo_regular", "القاهرة", R.font.cairo_regular),
    ArabicFontChoice("noto_naskh_regular", "نسخ عربي", R.font.noto_naskh_regular),
    ArabicFontChoice("amiri_regular", "أميري", R.font.amiri_regular),
    ArabicFontChoice("tajawal_regular", "تجوال", R.font.tajawal_regular),
    ArabicFontChoice("scheherazade_regular", "شهرزاد", R.font.scheherazade_regular),
    ArabicFontChoice("el_messiri_regular", "المسيري", R.font.el_messiri_regular)
)

@Composable
fun OfficeEditorScreen(viewModel: MasrofViewModel, initialDocumentId: Long? = null, onBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var savedFiles by remember { mutableStateOf(emptyList<LocalOfficeDocument>()) }
    var current by remember { mutableStateOf<LocalOfficeDocument?>(null) }
    var saveMessage by remember { mutableStateOf("") }
    var localSaveMessage by remember { mutableStateOf("") }
    val roomDocuments by viewModel.allDocuments.collectAsState()
    val editorDocuments = remember(savedFiles, roomDocuments) {
        val byId = linkedMapOf<String, LocalOfficeDocument>()
        savedFiles.forEach { byId[it.id] = it }
        roomDocuments.mapNotNull(OfficeDocumentRecord::decode).forEach { roomFile ->
            val prior = byId[roomFile.id]
            if (prior == null || roomFile.updatedAt >= prior.updatedAt) byId[roomFile.id] = roomFile
        }
        byId.values.sortedByDescending(LocalOfficeDocument::updatedAt)
    }
    val bookTags = remember(roomDocuments) {
        roomDocuments.flatMap { it.tags.split(',').map(String::trim) }
            .filter { it.startsWith("دفتر:") }.distinct().sorted()
    }

    LaunchedEffect(Unit) {
        savedFiles = withContext(Dispatchers.IO) { LocalOfficeDocumentStore.list(context) }
    }
    LaunchedEffect(initialDocumentId, roomDocuments) {
        if (initialDocumentId != null && current == null) {
            roomDocuments.firstOrNull { it.id == initialDocumentId }?.let(OfficeDocumentRecord::decode)?.let { current = it }
        }
    }
    LaunchedEffect(current) {
        val document = current ?: return@LaunchedEffect
        delay(450)
        runCatching {
            withContext(Dispatchers.IO) { LocalOfficeDocumentStore.save(context, document) }
            savedFiles = withContext(Dispatchers.IO) { LocalOfficeDocumentStore.list(context) }
            localSaveMessage = "حفظ محلي تلقائي"
        }.onFailure { localSaveMessage = "تعذر الحفظ المحلي: ${it.message.orEmpty()}" }
    }

    val openHome: () -> Unit = {
        val latest = current
        if (latest == null) onBack() else viewModel.saveOfficeDocument(context, latest) { linked, message ->
            saveMessage = message
            if (linked != null) current = null
            scope.launch { savedFiles = withContext(Dispatchers.IO) { LocalOfficeDocumentStore.list(context) } }
        }
    }

    val document = current
    BackHandler(enabled = document != null) { openHome() }
    if (document == null) {
        OfficeEditorHome(
            documents = editorDocuments,
            bookTags = bookTags,
            saveMessage = saveMessage,
            onBack = onBack,
            onCreate = { kind, title, bookTag ->
                scope.launch {
                    runCatching {
                        withContext(Dispatchers.IO) { LocalOfficeDocumentStore.create(context, kind, title).copy(bookTag = bookTag) }
                    }.onSuccess { created ->
                        savedFiles = withContext(Dispatchers.IO) { LocalOfficeDocumentStore.list(context) }
                        current = created
                        viewModel.saveOfficeDocument(context, created) { linked, message ->
                            saveMessage = message
                            if (linked != null) current = current?.takeIf { it.id == linked.id }?.copy(roomDocumentId = linked.roomDocumentId, updatedAt = linked.updatedAt)
                            scope.launch { savedFiles = withContext(Dispatchers.IO) { LocalOfficeDocumentStore.list(context) } }
                        }
                    }.onFailure { saveMessage = "تعذر إنشاء الصفحة: ${it.message.orEmpty()}" }
                }
            },
            onOpen = { current = it }
        )
    } else {
        Column(Modifier.fillMaxSize().background(Color(0xffeef2f5))) {
            Row(
                modifier = Modifier.fillMaxWidth().background(Color.White).padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                OutlinedButton(onClick = openHome) { Text("المستندات") }
                OutlinedTextField(
                    value = document.title,
                    onValueChange = { value -> current = document.copy(title = value, updatedAt = System.currentTimeMillis()) },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    label = { Text("اسم الملف") }
                )
                TextButton(onClick = {
                    viewModel.saveOfficeDocument(context, current ?: document) { linked, message ->
                        saveMessage = message
                        if (linked != null) current = current?.takeIf { it.id == linked.id }?.copy(roomDocumentId = linked.roomDocumentId, updatedAt = linked.updatedAt)
                        scope.launch { savedFiles = withContext(Dispatchers.IO) { LocalOfficeDocumentStore.list(context) } }
                    }
                }) { Text("حفظ") }
            }
            Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(document.kind.extensionLabel, style = MaterialTheme.typography.labelLarge, color = Color(0xff16486d))
                Text(listOf(saveMessage, localSaveMessage).filter(String::isNotBlank).joinToString(" • "), style = MaterialTheme.typography.labelSmall, color = Color(0xff25704b))
            }
            when (document.kind) {
                OfficeDocumentKind.WORD -> WordPageEditor(
                    document = document,
                    onContentChange = { html -> current = current?.copy(wordHtml = html, updatedAt = System.currentTimeMillis()) },
                    onFontChange = { font -> current = current?.copy(fontFamily = font, updatedAt = System.currentTimeMillis()) }
                )
                OfficeDocumentKind.EXCEL -> SpreadsheetPageEditor(
                    document = document,
                    onCellChange = { address, value ->
                        current?.let { latest -> current = latest.copy(cells = latest.cells + (address to value), updatedAt = System.currentTimeMillis()) }
                    },
                    onFontChange = { font -> current?.let { latest -> current = latest.copy(fontFamily = font, updatedAt = System.currentTimeMillis()) } }
                )
            }
        }
    }
}

@Composable
private fun OfficeEditorHome(
    documents: List<LocalOfficeDocument>,
    bookTags: List<String>,
    saveMessage: String,
    onBack: () -> Unit,
    onCreate: (OfficeDocumentKind, String, String) -> Unit,
    onOpen: (LocalOfficeDocument) -> Unit
) {
    var selectedKind by remember { mutableStateOf(OfficeDocumentKind.WORD) }
    var title by remember { mutableStateOf("مستند جديد") }
    var selectedBookTag by remember { mutableStateOf("") }
    var bookMenu by remember { mutableStateOf(false) }
    Column(
        Modifier.fillMaxSize().background(Color(0xffeef2f5)).verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = onBack) { Text("رجوع") }
            Text("محرر وورد", style = MaterialTheme.typography.headlineSmall, color = Color(0xff16486d))
        }
        Text("تُحفظ الصفحات محليًا وفي سجل المستندات؛ وتُزامن سحابيًا عند توفر حساب وجهاز معتمدين.", style = MaterialTheme.typography.bodyMedium)
        if (saveMessage.isNotBlank()) Text(saveMessage, style = MaterialTheme.typography.bodySmall, color = Color(0xff25704b))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OfficeDocumentKind.values().forEach { kind ->
                if (selectedKind == kind) Button(onClick = { selectedKind = kind; title = if (kind == OfficeDocumentKind.WORD) "مستند جديد" else "جدول بيانات جديد" }, modifier = Modifier.weight(1f)) { Text(kind.extensionLabel) }
                else OutlinedButton(onClick = { selectedKind = kind; title = if (kind == OfficeDocumentKind.WORD) "مستند جديد" else "جدول بيانات جديد" }, modifier = Modifier.weight(1f)) { Text(kind.extensionLabel) }
            }
        }
        OutlinedTextField(value = title, onValueChange = { title = it }, modifier = Modifier.fillMaxWidth(), singleLine = true, label = { Text("اسم الصفحة") })
        Box {
            OutlinedButton(onClick = { bookMenu = true }, modifier = Modifier.fillMaxWidth()) {
                Text(if (selectedBookTag.isBlank()) "إضافة اختيارية إلى دفتر مرقم" else "الدفتر: ${selectedBookTag.removePrefix("دفتر:")}")
            }
            DropdownMenu(expanded = bookMenu, onDismissRequest = { bookMenu = false }) {
                DropdownMenuItem(text = { Text("بدون إلحاق بدفتر") }, onClick = { selectedBookTag = ""; bookMenu = false })
                bookTags.forEach { tag ->
                    DropdownMenuItem(text = { Text(tag.removePrefix("دفتر:")) }, onClick = { selectedBookTag = tag; bookMenu = false })
                }
            }
        }
        Button(onClick = { onCreate(selectedKind, title, selectedBookTag) }, modifier = Modifier.fillMaxWidth()) {
            Text("إنشاء صفحة جديدة بيضاء")
        }
        HorizontalDivider()
        Text("ملفاتي المحلية (${documents.size})", style = MaterialTheme.typography.titleMedium)
        if (documents.isEmpty()) {
            Card(Modifier.fillMaxWidth()) { Text("لا توجد صفحات محفوظة حتى الآن.", Modifier.padding(16.dp)) }
        } else {
            documents.forEach { document ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { onOpen(document) },
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(document.title, style = MaterialTheme.typography.titleMedium)
                        Text("${document.kind.extensionLabel} • ${DateFormat.getDateTimeInstance().format(Date(document.updatedAt))}", style = MaterialTheme.typography.bodySmall, color = Color.DarkGray)
                        Text("اضغط للفتح والتحرير", style = MaterialTheme.typography.labelSmall, color = Color(0xff26714e))
                    }
                }
            }
        }
        Text("المرحلة الحالية توفر التحرير الأساسي دون الادعاء بمطابقة جميع وظائف Word/Excel المكتبيين. ستُضاف الأدوات المتقدمة على مراحل.", style = MaterialTheme.typography.bodySmall, color = Color.DarkGray)
    }
}

@Composable
private fun WordPageEditor(
    document: LocalOfficeDocument,
    onContentChange: (String) -> Unit,
    onFontChange: (String) -> Unit
) {
    val context = LocalContext.current
    val editorState = remember(document.id) { mutableStateOf<EditText?>(null) }
    Column(Modifier.fillMaxSize().padding(horizontal = 10.dp, vertical = 4.dp)) {
        WordToolbar(
            editor = editorState.value,
            fontKey = document.fontFamily,
            onFontChange = onFontChange
        )
        AndroidView(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(top = 6.dp, bottom = 8.dp)
                .border(1.dp, Color(0xffc7cdd3), RoundedCornerShape(4.dp)),
            factory = { viewContext ->
                EditText(viewContext).apply {
                    setBackgroundColor(android.graphics.Color.WHITE)
                    setPadding(26, 26, 26, 24)
                    minLines = 24
                    gravity = Gravity.TOP or Gravity.RIGHT
                    textDirection = View.TEXT_DIRECTION_RTL
                    textAlignment = View.TEXT_ALIGNMENT_VIEW_START
                    textSize = 18f
                    inputType = android.text.InputType.TYPE_CLASS_TEXT or
                        android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE or
                        android.text.InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
                    imeOptions = EditorInfo.IME_FLAG_NO_EXTRACT_UI
                    setText(Html.fromHtml(document.wordHtml, Html.FROM_HTML_MODE_LEGACY), TextView.BufferType.EDITABLE)
                    typeface = fontTypeface(context, document.fontFamily)
                    tag = document.wordHtml
                    editorState.value = this
                    addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
                        override fun afterTextChanged(s: Editable?) {
                            val html = Html.toHtml(s ?: return, Html.TO_HTML_PARAGRAPH_LINES_INDIVIDUAL)
                            tag = html
                            onContentChange(html)
                        }
                    })
                }
            },
            update = { view ->
                editorState.value = view
                view.typeface = fontTypeface(context, document.fontFamily)
                val stored = view.tag as? String
                if (stored != document.wordHtml && !view.hasFocus()) {
                    view.setText(Html.fromHtml(document.wordHtml, Html.FROM_HTML_MODE_LEGACY), TextView.BufferType.EDITABLE)
                    view.tag = document.wordHtml
                }
            }
        )
    }
}

@Composable
private fun WordToolbar(editor: EditText?, fontKey: String, onFontChange: (String) -> Unit) {
    val context = LocalContext.current
    var fontMenu by remember { mutableStateOf(false) }
    var sizeMenu by remember { mutableStateOf(false) }
    Row(
        Modifier.fillMaxWidth().background(Color.White, RoundedCornerShape(6.dp)).horizontalScroll(rememberScrollState()).padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = { applySelectedSpan(context, editor, StyleSpan(Typeface.BOLD)) }) { Text("عريض B") }
        TextButton(onClick = { applySelectedSpan(context, editor, StyleSpan(Typeface.ITALIC)) }) { Text("مائل I") }
        TextButton(onClick = { applySelectedSpan(context, editor, UnderlineSpan()) }) { Text("تسطير") }
        TextButton(onClick = { applySelectedSpan(context, editor, ForegroundColorSpan(Color(0xff16486d).toArgb())) }) { Text("لون") }
        TextButton(onClick = { applySelectedSpan(context, editor, BackgroundColorSpan(Color(0xffffef9b).toArgb())) }) { Text("تمييز") }
        TextButton(onClick = { applySelectedSpan(context, editor, AbsoluteSizeSpan(24, true)) }) { Text("حجم 24") }
        TextButton(onClick = { copySelection(context, editor, removeAfterCopy = false) }) { Text("نسخ") }
        TextButton(onClick = { copySelection(context, editor, removeAfterCopy = true) }) { Text("قص") }
        TextButton(onClick = { pasteClipboard(context, editor) }) { Text("لصق") }
        TextButton(onClick = {
            applySelectedSpan(context, editor, StyleSpan(Typeface.BOLD))
            applySelectedSpan(context, editor, AbsoluteSizeSpan(30, true))
        }) { Text("عنوان") }
        TextButton(onClick = { alignParagraph(editor, android.text.Layout.Alignment.ALIGN_OPPOSITE) }) { Text("يمين") }
        TextButton(onClick = { alignParagraph(editor, android.text.Layout.Alignment.ALIGN_CENTER) }) { Text("وسط") }
        TextButton(onClick = { alignParagraph(editor, android.text.Layout.Alignment.ALIGN_NORMAL) }) { Text("يسار") }
        TextButton(onClick = { insertBullet(editor) }) { Text("• قائمة") }
        TextButton(onClick = { insertNumberedItem(editor) }) { Text("١. قائمة") }
        TextButton(onClick = { insertAtCursor(editor, "\n────────────────────\n") }) { Text("فاصل") }
        TextButton(onClick = { insertAtCursor(editor, "\n${DateFormat.getDateInstance(DateFormat.SHORT).format(Date())}\n") }) { Text("التاريخ") }
        Box {
            TextButton(onClick = { fontMenu = true }) { Text("الخط") }
            DropdownMenu(expanded = fontMenu, onDismissRequest = { fontMenu = false }) {
                ArabicFonts.forEach { font ->
                    DropdownMenuItem(text = { Text(font.label) }, onClick = { onFontChange(font.key); fontMenu = false })
                }
            }
        }
        Box {
            TextButton(onClick = { sizeMenu = true }) { Text("حجم الخط") }
            DropdownMenu(expanded = sizeMenu, onDismissRequest = { sizeMenu = false }) {
                listOf(14, 16, 18, 24, 32).forEach { size ->
                    DropdownMenuItem(text = { Text("$size") }, onClick = { applySelectedSpan(context, editor, AbsoluteSizeSpan(size, true)); sizeMenu = false })
                }
            }
        }
    }
}

private fun copySelection(context: Context, editor: EditText?, removeAfterCopy: Boolean) {
    editor ?: return
    val start = minOf(editor.selectionStart, editor.selectionEnd).coerceAtLeast(0)
    val end = maxOf(editor.selectionStart, editor.selectionEnd).coerceAtLeast(0)
    if (start == end) {
        Toast.makeText(context, "حدد نصًا أولًا.", Toast.LENGTH_SHORT).show()
        return
    }
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(ClipData.newPlainText("office_selection", editor.text.subSequence(start, end)))
    if (removeAfterCopy) editor.text.delete(start, end)
}

private fun pasteClipboard(context: Context, editor: EditText?) {
    editor ?: return
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val value = clipboard.primaryClip?.getItemAt(0)?.coerceToText(context)?.toString().orEmpty()
    if (value.isNotEmpty()) insertAtCursor(editor, value)
}

private fun insertAtCursor(editor: EditText?, value: String) {
    editor ?: return
    val start = minOf(editor.selectionStart, editor.selectionEnd).coerceAtLeast(0)
    val end = maxOf(editor.selectionStart, editor.selectionEnd).coerceAtLeast(0)
    editor.text.replace(start, end, value)
}

private fun applySelectedSpan(context: android.content.Context, editor: EditText?, span: Any) {
    if (editor == null) return
    val start = minOf(editor.selectionStart, editor.selectionEnd).coerceAtLeast(0)
    val end = maxOf(editor.selectionStart, editor.selectionEnd).coerceAtLeast(0)
    if (start == end) {
        Toast.makeText(context, "حدد النص أولًا لتطبيق الأداة.", Toast.LENGTH_SHORT).show()
        return
    }
    (editor.text as? Spannable)?.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
}

private fun alignParagraph(editor: EditText?, alignment: android.text.Layout.Alignment) {
    editor ?: return
    val text = editor.text as? Spannable ?: return
    val cursor = editor.selectionStart.coerceIn(0, text.length)
    val selectionEnd = editor.selectionEnd.coerceIn(0, text.length)
    val start = if (cursor == 0) 0 else text.toString().lastIndexOf('\n', cursor - 1).let { it + 1 }
    val endNewline = text.toString().indexOf('\n', maxOf(cursor, selectionEnd))
    val end = if (endNewline < 0) text.length else endNewline + 1
    if (end > start) text.setSpan(AlignmentSpan.Standard(alignment), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
}

private fun insertBullet(editor: EditText?) {
    editor ?: return
    val text = editor.text
    val cursor = editor.selectionStart.coerceIn(0, text.length)
    val lineStart = if (cursor == 0) 0 else text.toString().lastIndexOf('\n', cursor - 1) + 1
    text.insert(lineStart, "• ")
    editor.setSelection((cursor + 2).coerceAtMost(text.length))
}

private fun insertNumberedItem(editor: EditText?) {
    editor ?: return
    val text = editor.text
    val cursor = editor.selectionStart.coerceIn(0, text.length)
    val lineStart = if (cursor == 0) 0 else text.toString().lastIndexOf('\n', cursor - 1) + 1
    val number = text.toString().substring(0, lineStart).count { it == '\n' } + 1
    val prefix = "$number. "
    text.insert(lineStart, prefix)
    editor.setSelection((cursor + prefix.length).coerceAtMost(text.length))
}

private fun fontTypeface(context: android.content.Context, key: String): Typeface =
    ArabicFonts.firstOrNull { it.key == key }?.let { ResourcesCompat.getFont(context, it.resourceId) } ?: Typeface.DEFAULT

@Composable
private fun SpreadsheetPageEditor(
    document: LocalOfficeDocument,
    onCellChange: (String, String) -> Unit,
    onFontChange: (String) -> Unit
) {
    var selectedCell by remember(document.id) { mutableStateOf("A6") }
    var fontMenu by remember { mutableStateOf(false) }
    val cellValue = document.cells[selectedCell].orEmpty()
    Column(Modifier.fillMaxSize().padding(horizontal = 10.dp, vertical = 4.dp)) {
        Row(Modifier.fillMaxWidth().background(Color.White).padding(6.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(selectedCell, color = Color(0xff16486d), style = MaterialTheme.typography.titleSmall)
            key(selectedCell) {
                OutlinedTextField(
                    value = cellValue,
                    onValueChange = { value -> onCellChange(selectedCell, value) },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    label = { Text("قيمة الخلية أو المعادلة") }
                )
            }
        }
        Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(vertical = 4.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            listOf("SUM" to "جمع", "AVERAGE" to "متوسط", "MIN" to "أصغر", "MAX" to "أكبر", "PRODUCT" to "ضرب", "ROUND" to "تقريب").forEach { (function, label) ->
                OutlinedButton(onClick = {
                    val column = selectedCell.dropLastWhile(Char::isDigit).ifBlank { "A" }
                    val row = selectedCell.takeLastWhile(Char::isDigit).toIntOrNull() ?: 6
                    val firstRow = if (row > 1) 1 else 2
                    val lastRow = if (row > 1) row - 1 else 100
                    val formula = if (function == "ROUND") {
                        val source = cellValue.trim().let { value -> if (value.startsWith("=")) value.drop(1) else value }.ifBlank { "0" }
                        "=ROUND($source,2)"
                    } else "=$function($column$firstRow:$column$lastRow)"
                    onCellChange(selectedCell, formula)
                }) { Text(label) }
            }
            OutlinedButton(onClick = { onCellChange(selectedCell, "") }) { Text("مسح الخلية") }
            Box {
                OutlinedButton(onClick = { fontMenu = true }) { Text("خط عربي") }
                DropdownMenu(expanded = fontMenu, onDismissRequest = { fontMenu = false }) {
                    ArabicFonts.forEach { font ->
                        DropdownMenuItem(text = { Text(font.label) }, onClick = { onFontChange(font.key); fontMenu = false })
                    }
                }
            }
            Text("المتاح: العمليات الحسابية و SUM / AVERAGE / MIN / MAX / PRODUCT / ROUND / ABS", modifier = Modifier.align(Alignment.CenterVertically), style = MaterialTheme.typography.labelSmall)
        }
        SpreadsheetGrid(document, selectedCell, onSelect = { selectedCell = it }, onCellChange = onCellChange, fontKey = document.fontFamily, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun SpreadsheetGrid(
    document: LocalOfficeDocument,
    selectedCell: String,
    onSelect: (String) -> Unit,
    onCellChange: (String, String) -> Unit,
    fontKey: String,
    modifier: Modifier = Modifier
) {
    val columns = (1..20).map(::columnLabel)
    LazyColumn(modifier = modifier.fillMaxWidth().background(Color.White).border(1.dp, Color(0xffa9b1b8))) {
        item(key = "column_headers") {
            Row(Modifier.horizontalScroll(rememberScrollState()).background(Color(0xffdce8f3))) {
                Box(Modifier.width(44.dp).height(38.dp).border(0.5.dp, Color.LightGray), contentAlignment = Alignment.Center) { Text("#", style = MaterialTheme.typography.labelSmall) }
                columns.forEach { column ->
                    Box(Modifier.width(106.dp).height(38.dp).border(0.5.dp, Color.LightGray), contentAlignment = Alignment.Center) { Text(column, style = MaterialTheme.typography.labelMedium) }
                }
            }
        }
        items((1..100).toList(), key = { "row_$it" }) { row ->
            Row(Modifier.horizontalScroll(rememberScrollState())) {
                Box(Modifier.width(44.dp).height(46.dp).background(Color(0xfff1f4f7)).border(0.5.dp, Color.LightGray), contentAlignment = Alignment.Center) { Text(row.toString(), style = MaterialTheme.typography.labelSmall) }
                columns.forEachIndexed { index, column ->
                    val address = "$column$row"
                    val raw = document.cells[address].orEmpty()
                    val shown = if (raw.startsWith("=") && selectedCell != address) SpreadsheetFormulaEvaluator.evaluate(raw, document.cells) else raw
                    BasicTextField(
                        value = shown,
                        onValueChange = { onSelect(address); onCellChange(address, it) },
                        modifier = Modifier.width(106.dp).height(46.dp)
                            .background(if (selectedCell == address) Color(0xfff4faff) else Color.White)
                            .border(if (selectedCell == address) 1.dp else 0.5.dp, if (selectedCell == address) Color(0xff2878b9) else Color.LightGray)
                            .padding(horizontal = 5.dp, vertical = 12.dp)
                            .onFocusChanged { if (it.isFocused) onSelect(address) },
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 14.sp, color = Color(0xff1f2933), textAlign = TextAlign.Start, fontFamily = composeFontFamily(fontKey))
                    )
                }
            }
        }
    }
}

private fun columnLabel(index: Int): String {
    var value = index
    val result = StringBuilder()
    while (value > 0) {
        val remainder = (value - 1) % 26
        result.append(('A'.code + remainder).toChar())
        value = (value - 1) / 26
    }
    return result.reverse().toString()
}

private fun composeFontFamily(key: String): FontFamily {
    val font = ArabicFonts.firstOrNull { it.key == key } ?: ArabicFonts.first()
    return FontFamily(Font(font.resourceId))
}
