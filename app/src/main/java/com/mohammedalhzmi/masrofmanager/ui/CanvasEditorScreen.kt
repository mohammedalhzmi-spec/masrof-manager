package com.mohammedalhzmi.masrofmanager.ui

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohammedalhzmi.masrofmanager.data.*
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
    LaunchedEffect(type) { viewModel.loadDesign(type) }
    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri?.let { selected ->
            runCatching { context.contentResolver.takePersistableUriPermission(selected, android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION) }
            viewModel.addDesignElement(DesignElementEntity(0, 0, "IMAGE", selected.toString(), 55f, 150f, 220f, 150f, opacity = 0.8f, zIndex = (elements.maxOfOrNull { it.zIndex } ?: 0) + 1))
        }
    }
    Column(Modifier.fillMaxSize().padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.fillMaxWidth()) {
            Text("محرر ${typeName(type)}", style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f))
            TextButton(onClick = onBack) { Text("حفظ وخروج") }
        }
        LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp), modifier = Modifier.fillMaxWidth()) {
            item { Button(onClick = { showTextDialog = true }) { Text("إضافة نص") } }
            item { Button(onClick = { imagePicker.launch(arrayOf("image/*")) }) { Text("صورة") } }
            item { Button(onClick = { viewModel.addDesignElement(DesignElementEntity(0, 0, "RECT", "", 80f, 250f, 180f, 70f, zIndex = (elements.maxOfOrNull { it.zIndex } ?: 0) + 1)) }) { Text("شكل") } }
            item { OutlinedButton(onClick = { viewModel.undo() }) { Text("تراجع") } }
            item { OutlinedButton(onClick = { viewModel.redo() }) { Text("إعادة") } }
            item { OutlinedButton(enabled = selectedId != null, onClick = { elements.firstOrNull { it.id == selectedId }?.let(viewModel::copyElement) }) { Text("نسخ") } }
            item { OutlinedButton(onClick = { viewModel.pasteElement() }) { Text("لصق") } }
            item { OutlinedButton(enabled = selectedId != null, onClick = { elements.firstOrNull { it.id == selectedId }?.let { viewModel.moveLayer(it, 1) } }) { Text("للأمام") } }
            item { OutlinedButton(enabled = selectedId != null, onClick = { elements.firstOrNull { it.id == selectedId }?.let { viewModel.moveLayer(it, -1) } }) { Text("للخلف") } }
            item { OutlinedButton(enabled = selectedId != null, onClick = { elements.firstOrNull { it.id == selectedId }?.let { viewModel.updateDesignElement(it.copy(width = it.width + 12f, height = it.height + 8f)) } }) { Text("تكبير") } }
            item { OutlinedButton(enabled = selectedId != null, onClick = { elements.firstOrNull { it.id == selectedId }?.let { viewModel.updateDesignElement(it.copy(width = (it.width - 12f).coerceAtLeast(30f), height = (it.height - 8f).coerceAtLeast(24f))) } }) { Text("تصغير") } }
            item { OutlinedButton(enabled = selectedId != null, onClick = { elements.firstOrNull { it.id == selectedId }?.let { viewModel.updateDesignElement(it.copy(rotation = it.rotation + 15f)) } }) { Text("تدوير") } }
            item { OutlinedButton(enabled = elements.firstOrNull { it.id == selectedId }?.type == "TEXT", onClick = { showEditDialog = true }) { Text("تحرير النص") } }
            item { OutlinedButton(enabled = selectedId != null, onClick = { elements.firstOrNull { it.id == selectedId }?.let { viewModel.deleteDesignElement(it); selectedId = null } }) { Text("حذف") } }
        }
        Spacer(Modifier.height(8.dp))
        Box(Modifier.fillMaxWidth().weight(1f).background(Color(0xffeeeeee)), contentAlignment = Alignment.TopCenter) {
            Box(Modifier.width(360.dp).height(510.dp).background(Color.White).border(1.dp, Color.DarkGray)) {
                elements.filter { it.visible }.sortedBy { it.zIndex }.forEach { element ->
                    CanvasElement(element, selectedId == element.id, onSelect = { selectedId = element.id }, onMove = { dx, dy -> viewModel.updateDesignElement(element.copy(x = element.x + dx, y = element.y + dy)) })
                }
            }
        }
        Text("الطبقات", style = MaterialTheme.typography.titleMedium)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp), modifier = Modifier.fillMaxWidth()) {
            items(elements.sortedByDescending { it.zIndex }, key = { it.id }) { element ->
                FilterChip(selected = selectedId == element.id, onClick = { selectedId = element.id }, label = { Text("${element.type} #${element.id}") })
            }
        }
        Text("اضغط على العنصر لتحديده ثم اسحبه. المقابض والأبعاد تحفظ مباشرة في قاعدة البيانات.", style = MaterialTheme.typography.bodySmall)
    }
    if (showTextDialog) TextElementDialog(onDismiss = { showTextDialog = false }) { text, bold, italic, underline, family, color ->
        viewModel.addDesignElement(DesignElementEntity(0, 0, "TEXT", text, 40f, 70f, 280f, 52f, zIndex = (elements.maxOfOrNull { it.zIndex } ?: 0) + 1, bold = bold, italic = italic, underline = underline, fontFamily = family, textColor = color)); showTextDialog = false
    }
    if (showEditDialog) {
        val selected = elements.firstOrNull { it.id == selectedId }
        if (selected != null) TextElementDialog(initial = selected, onDismiss = { showEditDialog = false }) { text, bold, italic, underline, family, color -> viewModel.updateDesignElement(selected.copy(content = text, bold = bold, italic = italic, underline = underline, fontFamily = family, textColor = color)); showEditDialog = false }
    }
}

@Composable
private fun CanvasElement(element: DesignElementEntity, selected: Boolean, onSelect: () -> Unit, onMove: (Float, Float) -> Unit) {
    val modifier = Modifier.offset { IntOffset(element.x.roundToInt(), element.y.roundToInt()) }.size(element.width.dp, element.height.dp).graphicsLayer { rotationZ = element.rotation }.alpha(element.opacity).pointerInput(element.id, element.x, element.y) {
        detectDragGestures(onDragStart = { onSelect() }) { change, drag -> change.consume(); onMove(drag.x, drag.y) }
    }.then(if (selected) Modifier.border(2.dp, MaterialTheme.colorScheme.primary) else Modifier)
    when (element.type) {
        "TEXT" -> Text(element.content, modifier = modifier.padding(4.dp), color = runCatching { Color(android.graphics.Color.parseColor(element.textColor)) }.getOrDefault(Color.Black), fontSize = element.fontSize.sp, fontWeight = if (element.bold) FontWeight.Bold else FontWeight.Normal, fontStyle = if (element.italic) FontStyle.Italic else FontStyle.Normal, textDecoration = if (element.underline) TextDecoration.Underline else TextDecoration.None, fontFamily = when (element.fontFamily) { "SERIF" -> FontFamily.Serif; "MONOSPACE" -> FontFamily.Monospace; else -> FontFamily.SansSerif })
        "RECT" -> Box(modifier.background(runCatching { Color(android.graphics.Color.parseColor(element.fillColor)) }.getOrDefault(Color.Transparent)).border(element.strokeWidth.dp, runCatching { Color(android.graphics.Color.parseColor(element.strokeColor)) }.getOrDefault(Color.DarkGray)))
        "IMAGE" -> DocumentImage(element, modifier)
    }
}

@Composable
private fun DocumentImage(element: DesignElementEntity, modifier: Modifier) {
    val context = LocalContext.current
    val bitmap by produceState<android.graphics.Bitmap?>(null, element.content) { value = withContext(Dispatchers.IO) { runCatching { context.contentResolver.openInputStream(Uri.parse(element.content)).use(BitmapFactory::decodeStream) }.getOrNull() } }
    if (bitmap != null) androidx.compose.foundation.Image(bitmap!!.asImageBitmap(), null, modifier = modifier, contentScale = ContentScale.Fit) else Box(modifier.background(Color.LightGray), contentAlignment = Alignment.Center) { Text("صورة") }
}

@Composable
private fun TextElementDialog(initial: DesignElementEntity? = null, onDismiss: () -> Unit, onSave: (String, Boolean, Boolean, Boolean, String, String) -> Unit) {
    var text by remember { mutableStateOf(initial?.content ?: "نص جديد") }; var bold by remember { mutableStateOf(initial?.bold ?: false) }; var italic by remember { mutableStateOf(initial?.italic ?: false) }; var underline by remember { mutableStateOf(initial?.underline ?: false) }; var family by remember { mutableStateOf(initial?.fontFamily ?: "SANS") }; var color by remember { mutableStateOf(initial?.textColor ?: "#000000") }
    AlertDialog(onDismissRequest = onDismiss, title = { Text("إضافة مربع نص") }, text = { Column(verticalArrangement = Arrangement.spacedBy(6.dp)) { OutlinedTextField(text, { text = it }, label = { Text("النص") }); OutlinedTextField(color, { color = it }, label = { Text("اللون") }); Row { FilterChip(bold, { bold = !bold }, label = { Text("عريض") }); FilterChip(italic, { italic = !italic }, label = { Text("مائل") }); FilterChip(underline, { underline = !underline }, label = { Text("تحته خط") }) }; Row { listOf("SANS" to "Sans", "SERIF" to "Serif", "MONOSPACE" to "Mono").forEach { (key, label) -> TextButton(onClick = { family = key }) { Text(if (family == key) "$label ✓" else label) } } } } }, confirmButton = { Button(onClick = { onSave(text, bold, italic, underline, family, color) }) { Text("إضافة") } }, dismissButton = { TextButton(onClick = onDismiss) { Text("إلغاء") } })
}

private fun typeName(type: DocumentType) = when (type) { DocumentType.ORDER -> "أمر الصرف"; DocumentType.REQUEST -> "ورقة التقديم"; DocumentType.RECEIPT -> "ورقة الاستلام" }
