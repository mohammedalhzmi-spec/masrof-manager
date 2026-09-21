package com.mohammedalhzmi.masrofmanager.ui

import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohammedalhzmi.masrofmanager.data.*
import com.example.R
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.mohammedalhzmi.masrofmanager.util.HybridAiAssistant
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

@Composable
fun CanvasEditorScreen(viewModel: MasrofViewModel, type: DocumentType, onBack: () -> Unit) {
    val context = LocalContext.current
    val elements by viewModel.designElements.collectAsState()
    var selectedId by remember { mutableStateOf<Long?>(null) }
    var showTextDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var showPageDialog by remember { mutableStateOf(false) }
    var showAiDialog by remember { mutableStateOf(false) }
    var grid by remember { mutableStateOf(true) }
    val design by viewModel.activeDesign.collectAsState()
    val scope = rememberCoroutineScope()
    LaunchedEffect(type) { viewModel.loadDesign(type) }
    val selected = elements.firstOrNull { it.id == selectedId }
    val nextZ = (elements.maxOfOrNull { it.zIndex } ?: 0) + 1
    fun add(typeName: String, content: String = "", x: Float = 60f, y: Float = 160f, w: Float = 180f, h: Float = 70f) = viewModel.addDesignElement(DesignElementEntity(0, 0, typeName, content, x, y, w, h, zIndex = nextZ))
    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri?.let { selectedUri ->
            runCatching { context.contentResolver.takePersistableUriPermission(selectedUri, android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION) }
            add("IMAGE", selectedUri.toString(), 55f, 150f, 220f, 150f)
        }
    }
    Column(Modifier.fillMaxSize().padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text("محرر ${typeName(type)}", style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f))
            TextButton(onClick = onBack) { Text("حفظ وخروج") }
        }
        LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp), modifier = Modifier.fillMaxWidth()) {
            item { Button(onClick = { showTextDialog = true }) { Text("نص") } }
            item { Button(onClick = { imagePicker.launch(arrayOf("image/*")) }) { Text("صورة") } }
            item { Button(onClick = { add("RECT") }) { Text("مستطيل") } }
            item { Button(onClick = { add("CIRCLE", x = 90f, y = 260f) }) { Text("دائرة") } }
            item { Button(onClick = { add("LINE", x = 70f, y = 340f, w = 230f, h = 8f) }) { Text("خط") } }
            item { Button(onClick = { add("ARROW", x = 70f, y = 380f, w = 230f, h = 24f) }) { Text("سهم") } }
            item { Button(onClick = { add("STICKER", "★", x = 140f, y = 420f, w = 58f, h = 58f) }) { Text("ملصق") } }
            item { Button(onClick = { add("QR", "{رقم المستند}", x = 230f, y = 420f, w = 80f, h = 80f) }) { Text("QR") } }
            item { OutlinedButton(onClick = { showPageDialog = true }) { Text("إعدادات الصفحة") } }
            item { Button(onClick = { showAiDialog = true }) { Text("المساعد الذكي") } }
            item { OutlinedButton(onClick = { grid = !grid }) { Text(if (grid) "شبكة: تشغيل" else "شبكة: إيقاف") } }
            item { OutlinedButton(onClick = { viewModel.undo() }) { Text("تراجع") } }
            item { OutlinedButton(onClick = { viewModel.redo() }) { Text("إعادة") } }
            item { OutlinedButton(enabled = selected != null, onClick = { selected?.let(viewModel::copyElement) }) { Text("نسخ") } }
            item { OutlinedButton(onClick = { viewModel.pasteElement() }) { Text("لصق") } }
            item { OutlinedButton(enabled = selected != null, onClick = { selected?.let { viewModel.moveLayer(it, 1) } }) { Text("للأمام") } }
            item { OutlinedButton(enabled = selected != null, onClick = { selected?.let { viewModel.moveLayer(it, -1) } }) { Text("للخلف") } }
            item { OutlinedButton(enabled = selected?.type == "TEXT", onClick = { selected?.let { viewModel.updateDesignElement(it.copy(fontSize = (it.fontSize + 2f).coerceAtMost(96f))) } }) { Text("A+") } }
            item { OutlinedButton(enabled = selected?.type == "TEXT", onClick = { selected?.let { viewModel.updateDesignElement(it.copy(fontSize = (it.fontSize - 2f).coerceAtLeast(8f))) } }) { Text("A-") } }
            item { OutlinedButton(enabled = selected?.type == "TEXT", onClick = { selected?.let { viewModel.updateDesignElement(it.copy(textAlign = "START")) } }) { Text("يمين") } }
            item { OutlinedButton(enabled = selected?.type == "TEXT", onClick = { selected?.let { viewModel.updateDesignElement(it.copy(textAlign = "CENTER")) } }) { Text("وسط") } }
            item { OutlinedButton(enabled = selected?.type == "TEXT", onClick = { selected?.let { viewModel.updateDesignElement(it.copy(textAlign = "END")) } }) { Text("يسار") } }
            item { OutlinedButton(enabled = selected?.type == "TEXT", onClick = { showEditDialog = true }) { Text("تحرير") } }
            item { OutlinedButton(enabled = selected != null, onClick = { selected?.let { viewModel.deleteDesignElement(it); selectedId = null } }) { Text("حذف") } }
        }
        Spacer(Modifier.height(8.dp))
        Box(Modifier.fillMaxWidth().weight(1f).background(if (grid) Color(0xffe6e8eb) else Color(0xffeeeeee)), contentAlignment = Alignment.TopCenter) {
            val landscape = design?.orientation == "LANDSCAPE"
            Box(Modifier.width(if (landscape) 510.dp else 360.dp).height(if (landscape) 360.dp else 510.dp).background(parseColor(design?.backgroundColor ?: "#FFFFFF")).border(1.dp, Color.DarkGray)) {
                elements.filter { it.visible }.sortedBy { it.zIndex }.forEach { element ->
                    CanvasElement(element, selectedId == element.id, grid, onSelect = { selectedId = element.id }, onMove = { dx, dy ->
                        val step = if (grid) 8f else 1f
                        viewModel.updateDesignElement(element.copy(x = element.x + (dx / step).roundToInt() * step, y = element.y + (dy / step).roundToInt() * step))
                    }, onResize = { dw, dh -> viewModel.updateDesignElement(element.copy(width = (element.width + dw).coerceAtLeast(24f), height = (element.height + dh).coerceAtLeast(18f))) }, onRotate = { viewModel.updateDesignElement(element.copy(rotation = element.rotation + it)) })
                }
            }
        }
        Text("مدير الطبقات", style = MaterialTheme.typography.titleMedium)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp), modifier = Modifier.fillMaxWidth()) {
            items(elements.sortedByDescending { it.zIndex }, key = { it.id }) { element -> FilterChip(selected = selectedId == element.id, onClick = { selectedId = element.id }, label = { Text("${element.type} #${element.id}") }) }
        }
        Text("اسحب العنصر للتحريك، اسحب المقبض السفلي لتغيير الحجم، والمقبض العلوي للدوران. تحفظ التعديلات مباشرة.", style = MaterialTheme.typography.bodySmall)
    }
    if (showTextDialog) TextElementDialog(onDismiss = { showTextDialog = false }) { text, bold, italic, underline, family, color, size, align, spacing ->
        viewModel.addDesignElement(DesignElementEntity(0, 0, "TEXT", text, 40f, 70f, 280f, 70f, zIndex = nextZ, bold = bold, italic = italic, underline = underline, fontFamily = family, textColor = color, fontSize = size, textAlign = align, lineSpacing = spacing)); showTextDialog = false
    }
    if (showEditDialog && selected != null) TextElementDialog(initial = selected, onDismiss = { showEditDialog = false }) { text, bold, italic, underline, family, color, size, align, spacing ->
        viewModel.updateDesignElement(selected.copy(content = text, bold = bold, italic = italic, underline = underline, fontFamily = family, textColor = color, fontSize = size, textAlign = align, lineSpacing = spacing)); showEditDialog = false
    }
    if (showPageDialog && design != null) PageSettingsDialog(design!!, onDismiss = { showPageDialog = false }) { updated -> viewModel.updateDesign(updated); showPageDialog = false }
    if (showAiDialog) AiLayoutDialog(onDismiss = { showAiDialog = false }) { key, endpoint, instruction, status ->
        scope.launch {
            status("جارٍ تحليل الطلب…")
            runCatching { HybridAiAssistant.plan(context, key, endpoint, instruction, elements, design) }
                .onSuccess { result -> viewModel.applyAiCommands(result.commands); status("${result.mode}: تم تطبيق ${result.commands.size} أمرًا على التصميم") }
                .onFailure { status("تعذر التنفيذ: ${it.message ?: "خطأ غير معروف"}") }
        }
    }
}

@Composable
private fun CanvasElement(element: DesignElementEntity, selected: Boolean, grid: Boolean, onSelect: () -> Unit, onMove: (Float, Float) -> Unit, onResize: (Float, Float) -> Unit, onRotate: (Float) -> Unit) {
    val root = Modifier.offset { IntOffset(element.x.roundToInt(), element.y.roundToInt()) }.size(element.width.dp, element.height.dp).graphicsLayer { rotationZ = element.rotation }.alpha(element.opacity).pointerInput(element.id, element.x, element.y) { detectDragGestures(onDragStart = { onSelect() }) { change, drag -> change.consume(); onMove(drag.x, drag.y) } }
    Box(root.then(if (selected) Modifier.border(2.dp, MaterialTheme.colorScheme.primary) else Modifier)) {
        when (element.type) {
            "TEXT" -> Text(element.content, Modifier.fillMaxSize().padding(4.dp), color = runCatching { Color(android.graphics.Color.parseColor(element.textColor)) }.getOrDefault(Color.Black), fontSize = element.fontSize.sp, fontWeight = if (element.bold) FontWeight.Bold else FontWeight.Normal, fontStyle = if (element.italic) FontStyle.Italic else FontStyle.Normal, textDecoration = if (element.underline) TextDecoration.Underline else TextDecoration.None, fontFamily = when (element.fontFamily) { "AMIRI" -> FontFamily(Font(R.font.amiri_regular)); "CAIRO" -> FontFamily(Font(R.font.cairo_regular)); "SCHEHERAZADE" -> FontFamily(Font(R.font.scheherazade_regular)); "EL_MESSIRI" -> FontFamily(Font(R.font.el_messiri_regular)); "NOTO_KUFI" -> FontFamily(Font(R.font.noto_kufi_regular)); "NOTO_NASKH" -> FontFamily(Font(R.font.noto_naskh_regular)); "TAJAWAL" -> FontFamily(Font(R.font.tajawal_regular)); "SERIF" -> FontFamily.Serif; "MONOSPACE" -> FontFamily.Monospace; else -> FontFamily.SansSerif }, textAlign = when (element.textAlign) { "CENTER" -> TextAlign.Center; "END" -> TextAlign.End; else -> TextAlign.Start }, lineHeight = (element.fontSize * element.lineSpacing).sp)
            "RECT" -> Box(Modifier.fillMaxSize().clip(androidx.compose.foundation.shape.RoundedCornerShape(element.cornerRadius.dp)).background(parseColor(element.fillColor)).border(element.strokeWidth.dp, parseColor(element.strokeColor)))
            "CIRCLE" -> Box(Modifier.fillMaxSize().background(parseColor(element.fillColor), androidx.compose.foundation.shape.CircleShape).border(element.strokeWidth.dp, parseColor(element.strokeColor), androidx.compose.foundation.shape.CircleShape))
            "LINE" -> Box(Modifier.fillMaxWidth().height(element.strokeWidth.dp).align(Alignment.Center).background(parseColor(element.strokeColor)))
            "ARROW" -> Text("➜", Modifier.fillMaxSize(), color = parseColor(element.strokeColor), fontSize = element.height.sp, textAlign = TextAlign.Center)
            "STICKER" -> Text(element.content, Modifier.fillMaxSize(), fontSize = (element.height * .75f).sp, textAlign = TextAlign.Center)
            "QR" -> DocumentQr(element, Modifier.fillMaxSize())
            "IMAGE" -> DocumentImage(element, Modifier.fillMaxSize())
        }
        if (selected) {
            Box(Modifier.align(Alignment.BottomEnd).size(18.dp).background(MaterialTheme.colorScheme.primary).pointerInput(element.id, element.width, element.height) { detectDragGestures { change, drag -> change.consume(); onResize(drag.x, drag.y) } })
            Box(Modifier.align(Alignment.TopCenter).offset(y = (-22).dp).size(18.dp).background(MaterialTheme.colorScheme.secondary).pointerInput(element.id, element.rotation) { detectDragGestures { change, drag -> change.consume(); onRotate(drag.x / 3f) } })
        }
    }
}

private fun parseColor(value: String) = runCatching { Color(android.graphics.Color.parseColor(value)) }.getOrDefault(Color.Transparent)

@Composable
private fun DocumentQr(element: DesignElementEntity, modifier: Modifier) {
    val bitmap = remember(element.content, element.width.roundToInt(), element.height.roundToInt()) { runCatching {
        val matrix = MultiFormatWriter().encode(element.content, BarcodeFormat.QR_CODE, element.width.roundToInt().coerceAtLeast(64), element.height.roundToInt().coerceAtLeast(64))
        Bitmap.createBitmap(matrix.width, matrix.height, Bitmap.Config.ARGB_8888).also { out ->
            for (x in 0 until matrix.width) for (y in 0 until matrix.height) out.setPixel(x, y, if (matrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
        }
    }.getOrNull() }
    if (bitmap != null) androidx.compose.foundation.Image(bitmap.asImageBitmap(), "QR", modifier = modifier, contentScale = ContentScale.FillBounds) else Box(modifier.background(Color.White), contentAlignment = Alignment.Center) { Text("QR") }
}

@Composable
private fun DocumentImage(element: DesignElementEntity, modifier: Modifier) {
    val context = LocalContext.current
    val bitmap by produceState<android.graphics.Bitmap?>(null, element.content) { value = withContext(Dispatchers.IO) { runCatching { context.contentResolver.openInputStream(Uri.parse(element.content)).use(BitmapFactory::decodeStream) }.getOrNull() } }
    if (bitmap != null) androidx.compose.foundation.Image(bitmap!!.asImageBitmap(), null, modifier = modifier, contentScale = ContentScale.Fit) else Box(modifier.background(Color.LightGray), contentAlignment = Alignment.Center) { Text("صورة") }
}

@Composable
private fun TextElementDialog(initial: DesignElementEntity? = null, onDismiss: () -> Unit, onSave: (String, Boolean, Boolean, Boolean, String, String, Float, String, Float) -> Unit) {
    var text by remember { mutableStateOf(initial?.content ?: "نص جديد") }; var bold by remember { mutableStateOf(initial?.bold ?: false) }; var italic by remember { mutableStateOf(initial?.italic ?: false) }; var underline by remember { mutableStateOf(initial?.underline ?: false) }; var family by remember { mutableStateOf(initial?.fontFamily ?: "SANS") }; var color by remember { mutableStateOf(initial?.textColor ?: "#000000") }; var size by remember { mutableStateOf(initial?.fontSize ?: 18f) }; var align by remember { mutableStateOf(initial?.textAlign ?: "START") }; var spacing by remember { mutableStateOf(initial?.lineSpacing ?: 1f) }
    AlertDialog(onDismissRequest = onDismiss, title = { Text("مربع نص متقدم") }, text = { Column(verticalArrangement = Arrangement.spacedBy(6.dp)) { OutlinedTextField(text, { text = it }, label = { Text("النص؛ يدعم عدة أسطر وحقولًا مثل {المبلغ}") }); OutlinedTextField(color, { color = it }, label = { Text("اللون") }); Row { FilterChip(bold, { bold = !bold }, label = { Text("عريض") }); FilterChip(italic, { italic = !italic }, label = { Text("مائل") }); FilterChip(underline, { underline = !underline }, label = { Text("تحته خط") }) }; Row { listOf("START" to "يمين", "CENTER" to "وسط", "END" to "يسار").forEach { (key, label) -> TextButton(onClick = { align = key }) { Text(if (align == key) "$label ✓" else label) } } }; Row(verticalAlignment = Alignment.CenterVertically) { Text("الحجم ${size.roundToInt()}"); TextButton(onClick = { size = (size - 2).coerceAtLeast(8f) }) { Text("-") }; TextButton(onClick = { size = (size + 2).coerceAtMost(96f) }) { Text("+") } }; Row { listOf("SANS" to "Sans", "SERIF" to "Serif", "MONOSPACE" to "Mono", "AMIRI" to "Amiri", "CAIRO" to "Cairo", "SCHEHERAZADE" to "Scheherazade", "EL_MESSIRI" to "El Messiri", "NOTO_KUFI" to "Noto Kufi", "NOTO_NASKH" to "Noto Naskh", "TAJAWAL" to "Tajawal").forEach { (key, label) -> TextButton(onClick = { family = key }) { Text(if (family == key) "$label ✓" else label) } } } } }, confirmButton = { Button(onClick = { onSave(text, bold, italic, underline, family, color, size, align, spacing) }) { Text("حفظ") } }, dismissButton = { TextButton(onClick = onDismiss) { Text("إلغاء") } })
}

@Composable
private fun PageSettingsDialog(initial: DocumentDesignEntity, onDismiss: () -> Unit, onSave: (DocumentDesignEntity) -> Unit) {
    var orientation by remember { mutableStateOf(initial.orientation) }
    var width by remember { mutableStateOf(initial.pageWidth) }
    var height by remember { mutableStateOf(initial.pageHeight) }
    var margin by remember { mutableStateOf(initial.marginLeft) }
    var background by remember { mutableStateOf(initial.backgroundColor) }
    AlertDialog(onDismissRequest = onDismiss, title = { Text("إعدادات الصفحة") }, text = { Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row { TextButton(onClick = { orientation = "PORTRAIT"; width = 595f; height = 842f }) { Text(if (orientation == "PORTRAIT") "عمودي ✓" else "عمودي") }; TextButton(onClick = { orientation = "LANDSCAPE"; width = 842f; height = 595f }) { Text(if (orientation == "LANDSCAPE") "أفقي ✓" else "أفقي") } }
        Row { TextButton(onClick = { width = 595f; height = 842f }) { Text("A4") }; TextButton(onClick = { width = 420f; height = 595f }) { Text("A5") }; Text("${width.roundToInt()} × ${height.roundToInt()}") }
        Row(verticalAlignment = Alignment.CenterVertically) { Text("الهامش ${margin.roundToInt()}"); TextButton(onClick = { margin = (margin - 5).coerceAtLeast(0f) }) { Text("-") }; TextButton(onClick = { margin += 5 }) { Text("+") } }
        OutlinedTextField(background, { background = it }, label = { Text("لون الخلفية #RRGGBB") })
    } }, confirmButton = { Button(onClick = { onSave(initial.copy(pageWidth = width, pageHeight = height, orientation = orientation, marginLeft = margin, marginTop = margin, marginRight = margin, marginBottom = margin, backgroundColor = background)) }) { Text("حفظ") } }, dismissButton = { TextButton(onClick = onDismiss) { Text("إلغاء") } })
}

@Composable
private fun AiLayoutDialog(onDismiss: () -> Unit, onRun: (String, String, String, (String) -> Unit) -> Unit) {
    var key by remember { mutableStateOf("") }
    var endpoint by remember { mutableStateOf("https://api.openai.com/v1/chat/completions") }
    var instruction by remember { mutableStateOf("نسق الصفحة بشكل رسمي، وحاذِ العنوان في الوسط وضع QR في الزاوية اليمنى السفلية") }
    var status by remember { mutableStateOf("المفتاح لا يُحفظ ولا يُضمن داخل التطبيق") }
    AlertDialog(onDismissRequest = onDismiss, title = { Text("المساعد الذكي الهجين") }, text = { Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("يعمل محليًا دون اتصال بقواعد تنسيق آمنة، ويستخدم المساعد السحابي عند توفر الشبكة والمفتاح لطلبات اللغة الحرة.", style = MaterialTheme.typography.bodySmall)
        OutlinedTextField(key, { key = it }, label = { Text("مفتاح API الخاص بك") }, singleLine = true)
        OutlinedTextField(endpoint, { endpoint = it }, label = { Text("رابط OpenAI-compatible") }, singleLine = true)
        OutlinedTextField(instruction, { instruction = it }, label = { Text("طلبك للمساعد") }, minLines = 3)
        Text(status, style = MaterialTheme.typography.bodySmall)
    } }, confirmButton = { Button(enabled = instruction.isNotBlank(), onClick = { onRun(key, endpoint, instruction) { status = it } }) { Text("تحليل وتطبيق") } }, dismissButton = { TextButton(onClick = onDismiss) { Text("إغلاق") } })
}

private fun typeName(type: DocumentType) = when (type) { DocumentType.ORDER -> "أمر الصرف"; DocumentType.REQUEST -> "ورقة التقديم"; DocumentType.RECEIPT -> "ورقة الاستلام" }
