package com.mohammedalhzmi.masrofmanager.ui

import android.graphics.Typeface
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.text.Editable
import android.text.Html
import android.text.Html.ImageGetter
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
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.rememberUpdatedState
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
import org.json.JSONObject
import androidx.core.content.res.ResourcesCompat
import android.provider.OpenableColumns
import com.example.R
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.util.LocalOfficeDocument
import com.mohammedalhzmi.masrofmanager.util.LocalOfficeDocumentStore
import com.mohammedalhzmi.masrofmanager.util.OfficeDocumentRecord
import com.mohammedalhzmi.masrofmanager.util.OfficeDocumentKind
import com.mohammedalhzmi.masrofmanager.util.OoxmlOfficeExchange
import com.mohammedalhzmi.masrofmanager.util.SpreadsheetFormulaEvaluator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DateFormat
import java.util.Date
import android.util.Base64
import java.io.ByteArrayOutputStream

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
    var imageToInsert by remember { mutableStateOf<String?>(null) }
    var imageWidthToInsert by remember { mutableStateOf(420) }
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

    fun importOffice(uri: Uri) {
        scope.launch {
            runCatching {
                val imported = withContext(Dispatchers.IO) {
                    val displayName = runCatching {
                        context.contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)?.use { cursor ->
                            if (cursor.moveToFirst()) cursor.getString(0) else null
                        }
                    }.getOrNull()?.takeIf(String::isNotBlank) ?: Uri.decode(uri.lastPathSegment.orEmpty()).ifBlank { "مستند مستورد" }
                    val mimeType = runCatching { context.contentResolver.getType(uri) }.getOrNull()
                    val input = context.contentResolver.openInputStream(uri) ?: error("تعذر قراءة الملف المحدد.")
                    OoxmlOfficeExchange.importOffice(input, displayName, mimeType)
                        .also { result -> LocalOfficeDocumentStore.save(context, result.document) }
                }
                savedFiles = withContext(Dispatchers.IO) { LocalOfficeDocumentStore.list(context) }
                current = imported.document
                saveMessage = (listOf("تم استيراد الملف وحفظ نسخة قابلة للتحرير داخل التطبيق.") + imported.notices).joinToString(" ")
                viewModel.saveOfficeDocument(context, imported.document) { linked, message ->
                    saveMessage = "${imported.notices.joinToString(" ")} $message".trim()
                    if (linked != null) current = current?.takeIf { it.id == linked.id }?.copy(roomDocumentId = linked.roomDocumentId, updatedAt = linked.updatedAt)
                    scope.launch { savedFiles = withContext(Dispatchers.IO) { LocalOfficeDocumentStore.list(context) } }
                }
            }.onFailure { saveMessage = "تعذر استيراد الملف: ${it.message.orEmpty()}" }
        }
    }

    fun exportOffice(uri: Uri?, kind: OfficeDocumentKind) {
        val documentToExport = current ?: return
        if (uri == null) return
        scope.launch {
            runCatching {
                val unsupportedFormulas = withContext(Dispatchers.IO) {
                    val output = context.contentResolver.openOutputStream(uri) ?: error("تعذر إنشاء ملف التصدير.")
                    output.use { stream ->
                        when (kind) {
                            OfficeDocumentKind.WORD -> OoxmlOfficeExchange.exportDocx(documentToExport, stream)
                            OfficeDocumentKind.EXCEL -> OoxmlOfficeExchange.exportXlsx(documentToExport, stream)
                        }
                    }
                }
                unsupportedFormulas
            }.onSuccess { result ->
                val count = result as? Int ?: 0
                saveMessage = if (count > 0) "تم التصدير؛ حُفظت $count صيغة غير مدعومة كنص لأسباب السلامة." else "تم تصدير الملف بصيغة ${if (kind == OfficeDocumentKind.WORD) "DOCX" else "XLSX"}."
            }
                .onFailure { saveMessage = "تعذر التصدير: ${it.message.orEmpty()}" }
        }
    }

    val importLauncher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri -> uri?.let(::importOffice) }
    val imageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri ?: return@rememberLauncherForActivityResult
        scope.launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    val source = context.contentResolver.openInputStream(uri) ?: error("تعذر قراءة الصورة المحددة.")
                    val raw = source.use { stream ->
                        val output = ByteArrayOutputStream()
                        val buffer = ByteArray(8192)
                        var total = 0
                        while (true) {
                            val count = stream.read(buffer)
                            if (count < 0) break
                            total += count
                            require(total <= 4 * 1024 * 1024) { "حجم الصورة أكبر من 4 ميجابايت." }
                            output.write(buffer, 0, count)
                        }
                        output.toByteArray()
                    }
                    val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
                    BitmapFactory.decodeByteArray(raw, 0, raw.size, bounds)
                    require(bounds.outWidth in 1..100_000 && bounds.outHeight in 1..100_000) { "صيغة الصورة غير مدعومة أو أبعادها غير صالحة." }
                    val targetWidth = imageWidthToInsert.coerceIn(160, 1000)
                    val sample = generateSequence(1) { it * 2 }.takeWhile { it <= 128 }
                        .lastOrNull { bounds.outWidth / it >= targetWidth * 2 || (bounds.outWidth / it.toLong()) * (bounds.outHeight / it.toLong()) > 16_000_000L } ?: 1
                    val decoded = BitmapFactory.decodeByteArray(raw, 0, raw.size, BitmapFactory.Options().apply { inSampleSize = sample })
                        ?: error("تعذر فك ترميز الصورة المحددة.")
                    val scale = minOf(targetWidth.toFloat() / decoded.width, 2200f / decoded.height, 1f)
                    val resized = if (scale < 1f) Bitmap.createScaledBitmap(decoded, (decoded.width * scale).toInt().coerceAtLeast(1), (decoded.height * scale).toInt().coerceAtLeast(1), true) else decoded
                    val output = ByteArrayOutputStream()
                    require(resized.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, output)) { "تعذر تجهيز الصورة للإدراج." }
                    require(output.size() <= 4 * 1024 * 1024) { "الصورة أكبر من حد الإدراج بعد تجهيزها." }
                    if (resized !== decoded) resized.recycle()
                    if (decoded !== resized) decoded.recycle()
                    "data:image/png;base64,${Base64.encodeToString(output.toByteArray(), Base64.NO_WRAP)}"
                }
            }.onSuccess { imageToInsert = it; saveMessage = "تم تحميل الصورة؛ اضغط حفظ لإدراجها في الصفحة." }
                .onFailure { saveMessage = "تعذر تحميل الصورة: ${it.message.orEmpty()}" }
        }
    }
    val docxExportLauncher = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument(OoxmlOfficeExchange.DOCX_MIME)) { uri -> exportOffice(uri, OfficeDocumentKind.WORD) }
    val xlsxExportLauncher = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument(OoxmlOfficeExchange.XLSX_MIME)) { uri -> exportOffice(uri, OfficeDocumentKind.EXCEL) }

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
            onImport = { importLauncher.launch(arrayOf("*/*")) },
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
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(horizontal = 8.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                OutlinedButton(onClick = {
                    val name = document.title.trim().ifBlank { if (document.kind == OfficeDocumentKind.WORD) "مستند" else "جدول" }
                    if (document.kind == OfficeDocumentKind.WORD) docxExportLauncher.launch("$name.docx")
                    else xlsxExportLauncher.launch("$name.xlsx")
                }) { Text(if (document.kind == OfficeDocumentKind.WORD) "تصدير DOCX" else "تصدير XLSX") }
            }
            when (document.kind) {
                OfficeDocumentKind.WORD -> WordPageEditor(
                    document = document,
                    onContentChange = { html -> current = current?.copy(wordHtml = html, updatedAt = System.currentTimeMillis()) },
                    onFontChange = { font -> current = current?.copy(fontFamily = font, updatedAt = System.currentTimeMillis()) },
                    imageToInsert = imageToInsert,
                    imageWidthToInsert = imageWidthToInsert,
                    onImageConsumed = { imageToInsert = null },
                    onSave = {
                        viewModel.saveOfficeDocument(context, current ?: document) { linked, message ->
                            saveMessage = message
                            if (linked != null) current = current?.takeIf { it.id == linked.id }?.copy(roomDocumentId = linked.roomDocumentId, updatedAt = linked.updatedAt)
                            scope.launch { savedFiles = withContext(Dispatchers.IO) { LocalOfficeDocumentStore.list(context) } }
                        }
                    },
                    onPickImage = { width -> imageWidthToInsert = width; imageLauncher.launch(arrayOf("image/*")) },
                    onPageSettingsChange = { size, orientation, background ->
                        current = current?.copy(pageSize = size, orientation = orientation, pageBackground = background, updatedAt = System.currentTimeMillis())
                    }
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
    onImport: () -> Unit,
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
            Text("محرر Office", style = MaterialTheme.typography.headlineSmall, color = Color(0xff16486d))
        }
        Text("تُحفظ الصفحات محليًا وفي سجل المستندات؛ وتُزامن سحابيًا عند توفر حساب وجهاز معتمدين. استيراد/تصدير DOCX وXLSX يدعم النصوص والتنسيق الأساسي والورقة الأولى والصيغ المحلية المدعومة، وليس جميع خصائص Office.", style = MaterialTheme.typography.bodyMedium)
        if (saveMessage.isNotBlank()) Text(saveMessage, style = MaterialTheme.typography.bodySmall, color = Color(0xff25704b))
        OutlinedButton(onClick = onImport, modifier = Modifier.fillMaxWidth()) { Text("استيراد DOCX أو XLSX") }
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
        Text("مستندات Office (${documents.size})", style = MaterialTheme.typography.titleMedium)
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
    onFontChange: (String) -> Unit,
    imageToInsert: String?,
    imageWidthToInsert: Int,
    onImageConsumed: () -> Unit,
    onSave: () -> Unit,
    onPickImage: (Int) -> Unit,
    onPageSettingsChange: (String, String, String) -> Unit
) {
    val context = LocalContext.current
    val onContentChangeState by rememberUpdatedState(onContentChange)
    val editorState = remember(document.id) { mutableStateOf<WebView?>(null) }
    var showTools by remember(document.id) { mutableStateOf(false) }
        LaunchedEffect(imageToInsert, editorState.value) {
        val data = imageToInsert ?: return@LaunchedEffect
        val editor = editorState.value ?: return@LaunchedEffect
        val html = "<img src=\"$data\" alt=\"صورة\" style=\"width:${imageWidthToInsert.coerceIn(160, 1000)}px;height:auto\" />"
        editor.evaluateJavascript("window.OfficeEditor && window.OfficeEditor.insertHtml(${JSONObject.quote(html)});", null)
        onImageConsumed()
    }
    Box(Modifier.fillMaxSize()) {
      Column(Modifier.fillMaxSize().padding(horizontal = 10.dp, vertical = 4.dp)) {
        Row(
            Modifier.fillMaxWidth().background(Color.White).padding(horizontal = 8.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            TextButton(onClick = { editorState.value?.clearFocus() }) { Text("✕", fontSize = 24.sp) }
            Text("20", modifier = Modifier.border(1.dp, Color(0xffb7bec5), RoundedCornerShape(4.dp)).padding(horizontal = 10.dp, vertical = 5.dp), fontSize = 16.sp)
            TextButton(onClick = { executeOfficeCommand(editorState.value, "undo") }) { Text("↶", fontSize = 24.sp, color = Color(0xff536170)) }
            TextButton(onClick = { executeOfficeCommand(editorState.value, "redo") }) { Text("↷", fontSize = 24.sp, color = Color(0xff536170)) }
            TextButton(onClick = onSave) { Text("حفظ", fontSize = 18.sp) }
            TextButton(onClick = { editorState.value?.clearFocus() }) { Text("تم", fontSize = 18.sp) }
        }
        if (showTools) WordToolbar(
            editor = editorState.value,
            fontKey = document.fontFamily,
            onFontChange = onFontChange,
            onPickImage = onPickImage,
            onInsertTable = { insertOfficeTable(editorState.value, 3, 3) },
            onClose = { showTools = false }
        )
        if (showTools) PageSettingsToolbar(document, onPageSettingsChange)
        AndroidView(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(top = 6.dp, bottom = 8.dp)
                .border(1.dp, Color(0xffc7cdd3), RoundedCornerShape(4.dp)),
                factory = { viewContext ->
                WebView(viewContext).apply {
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = false
                    settings.allowFileAccess = false
                    settings.allowContentAccess = false
                    settings.blockNetworkLoads = true
                    settings.javaScriptCanOpenWindowsAutomatically = false
                    settings.setSupportMultipleWindows(false)
                    webViewClient = object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean = true
                    }
                    setBackgroundColor(runCatching { android.graphics.Color.parseColor(document.pageBackground) }.getOrDefault(android.graphics.Color.WHITE))
                    addJavascriptInterface(OfficeEditorBridge(this, context) { html -> onContentChangeState(html) }, "OfficeBridge")
                    tag = document.wordHtml
                    editorState.value = this
                    loadDataWithBaseURL(null, officeEditorHtml(document, context), "text/html", "UTF-8", null)
                }
            },
            update = { view ->
                editorState.value = view
                view.setBackgroundColor(runCatching { android.graphics.Color.parseColor(document.pageBackground) }.getOrDefault(android.graphics.Color.WHITE))
                val stored = view.tag as? String
                if (stored != document.wordHtml && !view.hasFocus()) {
                    view.tag = document.wordHtml
                    view.loadDataWithBaseURL(null, officeEditorHtml(document, context), "text/html", "UTF-8", null)
                } else {
                    view.evaluateJavascript("window.OfficeEditor && window.OfficeEditor.updatePage(${JSONObject.quote(document.pageBackground)},${JSONObject.quote(document.orientation)},${JSONObject.quote(document.pageSize)},${JSONObject.quote(officeWebFontName(document.fontFamily))});", null)
                }
            }
        )
      }
      FloatingActionButton(
          onClick = { showTools = !showTools },
          modifier = Modifier.align(Alignment.BottomEnd).padding(end = 16.dp, bottom = 16.dp),
          containerColor = Color(0xffe2e2e2),
          contentColor = Color(0xff334155)
      ) { Text(if (showTools) "×" else "▦", fontSize = 26.sp) }
    }
}

private class OfficeEditorBridge(
    private val webView: WebView,
    context: Context,
    private val onChange: (String) -> Unit
) {
    private val mainHandler = Handler(Looper.getMainLooper())
    private val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    @JavascriptInterface
    fun onContentChanged(html: String) {
        if (html.length > 24 * 1024 * 1024) return
        mainHandler.post { webView.tag = html; onChange(html) }
    }

    @JavascriptInterface
    fun copySelection(text: String) {
        mainHandler.post {
            if (text.isNotEmpty()) clipboard.setPrimaryClip(ClipData.newPlainText("office_selection", text))
        }
    }
}

private fun officeEditorHtml(document: LocalOfficeDocument, context: Context): String {
    val content = sanitizeOfficeHtml(document.wordHtml)
    val background = document.pageBackground.takeIf { it.matches(Regex("#[0-9A-Fa-f]{6}")) } ?: "#FFFFFF"
    val orientation = document.orientation.takeIf { it == "LANDSCAPE" } ?: "PORTRAIT"
    val size = document.pageSize.takeIf { it in setOf("A4", "A5", "A3") } ?: "A4"
    val font = when (document.fontFamily) {
        "amiri_regular" -> "Amiri, serif"
        "tajawal_regular" -> "Tajawal, sans-serif"
        "noto_naskh_regular" -> "Noto Naskh Arabic, serif"
        "scheherazade_regular" -> "Scheherazade New, serif"
        "el_messiri_regular" -> "El Messiri, sans-serif"
        else -> "Cairo, sans-serif"
    }
    val fontName = officeWebFontName(document.fontFamily)
    val fontBytes = ArabicFonts.firstOrNull { it.key == document.fontFamily }?.let { choice ->
        runCatching { context.resources.openRawResource(choice.resourceId).use { it.readBytes() } }.getOrNull()
    }
    val fontFace = fontBytes?.takeIf { it.size <= 2 * 1024 * 1024 }?.let {
        "@font-face{font-family:'$fontName';src:url(data:font/ttf;base64,${Base64.encodeToString(it, Base64.NO_WRAP)}) format('truetype');font-weight:normal;font-style:normal;}"
    }.orEmpty()
    return """<!doctype html><html lang="ar"><head><meta charset="utf-8"><meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<style>$fontFace html,body{margin:0;min-height:100%;background:#eef2f5}body{font-family:'$fontName',${font.substringAfter(", ")};font-size:18px;line-height:1.75;color:#18222d}.page{box-sizing:border-box;width:100%;min-height:96vh;margin:0 auto;padding:24px 20px;background:$background;outline:none;overflow-wrap:anywhere}#office-page:focus{outline:none}img{max-width:100%;height:auto;vertical-align:middle}table{border-collapse:collapse;width:100%;table-layout:auto;margin:12px 0}td,th{border:1px solid #7b8794;padding:6px;min-width:34px;vertical-align:top}ul,ol{padding-inline-start:2em}blockquote{border-inline-start:3px solid #96a5b4;margin-inline:12px;padding-inline:10px}</style></head>
<body><main id="office-page" class="page" contenteditable="true" dir="rtl" spellcheck="true">$content</main>
<script>(function(){var e=document.getElementById('office-page');function report(){if(window.OfficeBridge)window.OfficeBridge.onContentChanged(e.innerHTML)}
document.addEventListener('input',report);document.addEventListener('click',function(ev){var a=ev.target.closest('a');if(a)ev.preventDefault()});
window.OfficeEditor={command:function(c,v){e.focus();document.execCommand(c,false,v==null?null:v);report()},insertHtml:function(h){e.focus();document.execCommand('insertHTML',false,h);report()},insertText:function(t){e.focus();document.execCommand('insertText',false,t);report()},selectedText:function(){return window.getSelection()?window.getSelection().toString():''},clear:function(){e.innerHTML='';report()},tableAction:function(action){var s=window.getSelection(),n=s&&s.anchorNode?s.anchorNode.parentElement:null,c=n&&n.closest('td,th');if(!c)return;var r=c.closest('tr'),t=c.closest('table');if(action==='add-row'){var nr=r.cloneNode(false);for(var i=0;i<r.cells.length;i++){var nc=r.cells[i].cloneNode(false);nc.innerHTML='<br>';nr.appendChild(nc)}r.parentNode.insertBefore(nr,r.nextSibling)}else if(action==='add-column'){var idx=c.cellIndex;Array.from(t.rows).forEach(function(row){var nc=row.insertCell(Math.min(idx+1,row.cells.length));nc.innerHTML='<br>'})}else if(action==='delete-row'){if(t.rows.length>1)r.remove()}else if(action==='delete-column'){var idx=c.cellIndex;Array.from(t.rows).forEach(function(row){if(row.cells.length>1&&row.cells[idx])row.deleteCell(idx)})}report()},updatePage:function(bg,orientation,size,font){e.style.background=/^#[0-9a-fA-F]{6}$/.test(bg)?bg:'#FFFFFF';e.style.fontFamily=font+',sans-serif';e.dataset.orientation=orientation;e.dataset.size=size;var h={A4:1122,A5:794,A3:1588}[size]||1122;e.style.minHeight=(orientation==='LANDSCAPE'?Math.round(h*0.707):h)+'px'}};window.OfficeEditor.updatePage('$background','$orientation','$size',${JSONObject.quote(officeWebFontName(document.fontFamily))});})();</script></body></html>"""
}

private fun sanitizeOfficeHtml(html: String): String {
    var safe = html
        .replace(Regex("(?is)<(script|iframe|object|embed|svg|math|form|input|video|audio)\\b[^>]*>.*?</\\1\\s*>"), "")
        .replace(Regex("(?is)<(script|iframe|object|embed|svg|math|form|input|video|audio)\\b[^>]*/?>"), "")
        .replace(Regex("(?is)<!--.*?-->"), "")
        .replace(Regex("(?i)\\s+on[a-z]+\\s*=\\s*(\"[^\"]*\"|'[^']*'|[^\\s>]+)"), "")
        .replace(Regex("(?i)javascript\\s*:"), "")
    val imgTag = Regex("(?is)<img\\b[^>]*>")
    safe = imgTag.replace(safe) { match ->
        val tag = match.value
        val src = Regex("(?i)\\bsrc\\s*=\\s*(['\"])(.*?)\\1").find(tag)?.groupValues?.get(2).orEmpty()
        if (src.startsWith("data:image/png;base64,") || src.startsWith("data:image/jpeg;base64,") ||
            src.startsWith("data:image/gif;base64,") || src.startsWith("data:image/webp;base64,") ||
            src.startsWith("data:image/bmp;base64,")) tag else ""
    }
    return safe
}

@Composable
private fun PageSettingsToolbar(document: LocalOfficeDocument, onChange: (String, String, String) -> Unit) {
    var sizeMenu by remember { mutableStateOf(false) }
    var directionMenu by remember { mutableStateOf(false) }
    Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).background(Color.White).padding(4.dp), horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
        Box {
            OutlinedButton(onClick = { sizeMenu = true }) { Text("الصفحة ${document.pageSize}") }
            DropdownMenu(sizeMenu, { sizeMenu = false }) {
                listOf("A4", "A5", "A3").forEach { size -> DropdownMenuItem(text = { Text(size) }, onClick = { onChange(size, document.orientation, document.pageBackground); sizeMenu = false }) }
            }
        }
        Box {
            OutlinedButton(onClick = { directionMenu = true }) { Text(if (document.orientation == "LANDSCAPE") "أفقي" else "رأسي") }
            DropdownMenu(directionMenu, { directionMenu = false }) {
                listOf("PORTRAIT" to "رأسي", "LANDSCAPE" to "أفقي").forEach { (value, label) -> DropdownMenuItem(text = { Text(label) }, onClick = { onChange(document.pageSize, value, document.pageBackground); directionMenu = false }) }
            }
        }
        Text("خلفية الصفحة", style = MaterialTheme.typography.labelMedium)
        listOf("#FFFFFF" to Color.White, "#FFFDF2" to Color(0xfffffdf2), "#F2F7FF" to Color(0xfff2f7ff), "#F5F5F5" to Color(0xfff5f5f5)).forEach { (hex, color) ->
            Button(onClick = { onChange(document.pageSize, document.orientation, hex) }, modifier = Modifier.width(44.dp).height(34.dp), contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp), colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = color)) { Text("") }
        }
    }
}

@Composable
private fun WordToolbar(editor: WebView?, fontKey: String, onFontChange: (String) -> Unit, onPickImage: (Int) -> Unit, onInsertTable: () -> Unit, onClose: () -> Unit) {
    val context = LocalContext.current
    var fontMenu by remember { mutableStateOf(false) }
    var sizeMenu by remember { mutableStateOf(false) }
    var tab by remember { mutableStateOf("قلم") }
    Column(Modifier.fillMaxWidth().background(Color(0xffe2e2e2), RoundedCornerShape(6.dp))) {
        Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(horizontal = 6.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("إدراج", "عرض", "مراجعة", "قلم").forEach { item ->
                TextButton(onClick = { tab = item }) { Text(item, color = if (tab == item) Color(0xff1675b8) else Color.DarkGray, fontSize = 16.sp) }
            }
            Text("⌄", modifier = Modifier.align(Alignment.CenterVertically), fontSize = 24.sp)
            TextButton(onClick = onClose) { Text("إخفاء") }
        }
        Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).background(Color.White).padding(5.dp), horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically) {
            when (tab) {
                "قلم" -> {
                    TextButton(onClick = { executeOfficeCommand(editor, "bold") }) { Text("B") }
                    TextButton(onClick = { executeOfficeCommand(editor, "italic") }) { Text("I") }
                    TextButton(onClick = { executeOfficeCommand(editor, "underline") }) { Text("U") }
                    TextButton(onClick = { executeOfficeCommand(editor, "foreColor", "#1675b8") }) { Text("لون النص") }
                    TextButton(onClick = { executeOfficeCommand(editor, "hiliteColor", "#fff09b") }) { Text("تمييز") }
                    Box { TextButton(onClick = { fontMenu = true }) { Text("الخط: ${ArabicFonts.firstOrNull { it.key == fontKey }?.label ?: "عربي"}") }; DropdownMenu(fontMenu, { fontMenu = false }) { ArabicFonts.forEach { font -> DropdownMenuItem(text = { Text(font.label) }, onClick = { installLocalOfficeFont(context, editor, font); onFontChange(font.key); executeOfficeCommand(editor, "fontName", officeWebFontName(font.key)); fontMenu = false }) } } }
                    Box { TextButton(onClick = { sizeMenu = true }) { Text("الحجم") }; DropdownMenu(sizeMenu, { sizeMenu = false }) { listOf(11, 14, 16, 20, 24, 32).forEach { size -> DropdownMenuItem(text = { Text("$size") }, onClick = { executeOfficeCommand(editor, "fontSize", htmlFontSize(size)); sizeMenu = false }) } } }
                }
                "عرض" -> {
                    TextButton(onClick = { executeOfficeCommand(editor, "justifyRight") }) { Text("يمين") }
                    TextButton(onClick = { executeOfficeCommand(editor, "justifyCenter") }) { Text("وسط") }
                    TextButton(onClick = { executeOfficeCommand(editor, "justifyLeft") }) { Text("يسار") }
                    TextButton(onClick = { executeOfficeCommand(editor, "insertUnorderedList") }) { Text("• قائمة") }
                    TextButton(onClick = { executeOfficeCommand(editor, "insertOrderedList") }) { Text("١. قائمة") }
                }
                "مراجعة" -> {
                    TextButton(onClick = { copySelection(context, editor, false) }) { Text("نسخ") }
                    TextButton(onClick = { copySelection(context, editor, true) }) { Text("قص") }
                    TextButton(onClick = { pasteClipboard(context, editor) }) { Text("لصق") }
                    TextButton(onClick = { executeOfficeJavascript(editor, "document.getElementById('office-page').focus();document.execCommand('selectAll')") }) { Text("تحديد الكل") }
                    TextButton(onClick = { executeOfficeJavascript(editor, "window.OfficeEditor.clear()") }) { Text("مسح الصفحة") }
                }
                else -> {
                    TextButton(onClick = onInsertTable) { Text("إدراج جدول ٣×٣") }
                    TextButton(onClick = { runOfficeTableAction(editor, "add-row") }) { Text("إضافة صف") }
                    TextButton(onClick = { runOfficeTableAction(editor, "add-column") }) { Text("إضافة عمود") }
                    TextButton(onClick = { runOfficeTableAction(editor, "delete-row") }) { Text("حذف الصف") }
                    TextButton(onClick = { runOfficeTableAction(editor, "delete-column") }) { Text("حذف العمود") }
                    TextButton(onClick = { onPickImage(240) }) { Text("صورة صغيرة") }
                    TextButton(onClick = { onPickImage(420) }) { Text("صورة متوسطة") }
                    TextButton(onClick = { onPickImage(620) }) { Text("صورة كبيرة") }
                    TextButton(onClick = { insertAtCursor(editor, "<hr>") }) { Text("فاصل") }
                    TextButton(onClick = { insertAtCursor(editor, "<p>${DateFormat.getDateInstance(DateFormat.SHORT).format(Date())}</p>") }) { Text("التاريخ") }
                }
            }
        }
    }
}

private fun executeOfficeCommand(editor: WebView?, command: String, value: String? = null) {
    val arg = value?.let(JSONObject::quote) ?: "null"
    executeOfficeJavascript(editor, "window.OfficeEditor && window.OfficeEditor.command(${JSONObject.quote(command)},$arg)")
}

private fun executeOfficeJavascript(editor: WebView?, script: String) {
    editor?.evaluateJavascript(script, null)
}

private fun copySelection(context: Context, editor: WebView?, removeAfterCopy: Boolean) {
    editor ?: return
    executeOfficeJavascript(editor, "window.OfficeBridge && window.OfficeBridge.copySelection(window.OfficeEditor.selectedText())")
    if (removeAfterCopy) executeOfficeCommand(editor, "delete")
}

private fun pasteClipboard(context: Context, editor: WebView?) {
    editor ?: return
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val value = clipboard.primaryClip?.getItemAt(0)?.coerceToText(context)?.toString().orEmpty()
    if (value.isNotEmpty()) executeOfficeJavascript(editor, "window.OfficeEditor && window.OfficeEditor.insertText(${JSONObject.quote(value)})")
}

private fun insertAtCursor(editor: WebView?, html: String) {
    executeOfficeJavascript(editor, "window.OfficeEditor && window.OfficeEditor.insertHtml(${JSONObject.quote(html)})")
}

private fun insertOfficeTable(editor: WebView?, rows: Int, columns: Int) {
    val table = buildString {
        append("<table border=\"1\"><tbody>")
        repeat(rows.coerceIn(1, 12)) {
            append("<tr>")
            repeat(columns.coerceIn(1, 10)) { append("<td><br></td>") }
            append("</tr>")
        }
        append("</tbody></table><p><br></p>")
    }
    insertAtCursor(editor, table)
}

private fun runOfficeTableAction(editor: WebView?, action: String) {
    executeOfficeJavascript(editor, "window.OfficeEditor && window.OfficeEditor.tableAction(${JSONObject.quote(action)})")
}

private fun officeWebFontName(key: String): String = when (key) {
    "amiri_regular" -> "Amiri"
    "tajawal_regular" -> "Tajawal"
    "noto_naskh_regular" -> "Noto Naskh Arabic"
    "scheherazade_regular" -> "Scheherazade New"
    "el_messiri_regular" -> "El Messiri"
    else -> "Cairo"
}

private fun installLocalOfficeFont(context: Context, editor: WebView?, choice: ArabicFontChoice) {
    editor ?: return
    val bytes = runCatching { context.resources.openRawResource(choice.resourceId).use { it.readBytes() } }.getOrNull() ?: return
    if (bytes.size > 2 * 1024 * 1024) return
    val css = "@font-face{font-family:'${officeWebFontName(choice.key)}';src:url(data:font/ttf;base64,${Base64.encodeToString(bytes, Base64.NO_WRAP)}) format('truetype');font-weight:normal;font-style:normal;}"
    val script = "(function(){var s=document.getElementById('office-local-font');if(!s){s=document.createElement('style');s.id='office-local-font';document.head.appendChild(s)}s.textContent=${JSONObject.quote(css)};document.getElementById('office-page').style.fontFamily=${JSONObject.quote(officeWebFontName(choice.key))}})()"
    executeOfficeJavascript(editor, script)
}

private fun htmlFontSize(size: Int): String = when {
    size <= 11 -> "2"
    size <= 14 -> "3"
    size <= 16 -> "4"
    size <= 20 -> "5"
    size <= 24 -> "6"
    else -> "7"
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
    val outOfViewCells = document.cells.keys.count { address ->
        spreadsheetColumnIndex(address) > 40 || (address.takeLastWhile(Char::isDigit).toIntOrNull() ?: 0) > 1000
    }
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
        if (outOfViewCells > 0) Text("$outOfViewCells خلية محفوظة خارج مساحة العرض (40 عمودًا × 1000 صف)؛ ستبقى ضمن التصدير.", style = MaterialTheme.typography.labelSmall, color = Color(0xff875c12))
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
    val validAddresses = document.cells.keys.filter { it.matches(Regex("[A-Z]{1,3}[1-9][0-9]{0,6}")) }
    val importedMaxColumn = validAddresses.maxOfOrNull(::spreadsheetColumnIndex) ?: 1
    val importedMaxRow = validAddresses.maxOfOrNull { it.takeLastWhile(Char::isDigit).toIntOrNull() ?: 1 } ?: 1
    val columns = (1..maxOf(20, importedMaxColumn.coerceAtMost(40))).map(::columnLabel)
    val visibleRows = maxOf(100, importedMaxRow.coerceAtMost(1000))
    LazyColumn(modifier = modifier.fillMaxWidth().background(Color.White).border(1.dp, Color(0xffa9b1b8))) {
        item(key = "column_headers") {
            Row(Modifier.horizontalScroll(rememberScrollState()).background(Color(0xffdce8f3))) {
                Box(Modifier.width(44.dp).height(38.dp).border(0.5.dp, Color.LightGray), contentAlignment = Alignment.Center) { Text("#", style = MaterialTheme.typography.labelSmall) }
                columns.forEach { column ->
                    Box(Modifier.width(106.dp).height(38.dp).border(0.5.dp, Color.LightGray), contentAlignment = Alignment.Center) { Text(column, style = MaterialTheme.typography.labelMedium) }
                }
            }
        }
        items((1..visibleRows).toList(), key = { "row_$it" }) { row ->
            Row(Modifier.horizontalScroll(rememberScrollState())) {
                Box(Modifier.width(44.dp).height(46.dp).background(Color(0xfff1f4f7)).border(0.5.dp, Color.LightGray), contentAlignment = Alignment.Center) { Text(row.toString(), style = MaterialTheme.typography.labelSmall) }
                columns.forEachIndexed { index, column ->
                    val address = "$column$row"
                    val raw = document.cells[address].orEmpty()
                    val evaluated = if (raw.startsWith("=") && selectedCell != address) SpreadsheetFormulaEvaluator.evaluate(raw, document.cells) else raw
                    val shown = if (evaluated.startsWith("#") && raw.startsWith("=")) raw else evaluated
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

private fun spreadsheetColumnIndex(address: String): Int = address.dropLastWhile(Char::isDigit)
    .fold(0) { sum, c -> sum * 26 + (c - 'A' + 1) }

private fun composeFontFamily(key: String): FontFamily {
    val font = ArabicFonts.firstOrNull { it.key == key } ?: ArabicFonts.first()
    return FontFamily(Font(font.resourceId))
}
