package com.mohammedalhzmi.masrofmanager.ui

import android.content.Context
import android.content.Context.PRINT_SERVICE
import android.print.PrintAttributes
import android.print.PrintManager
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.util.BranchAssetData
import com.mohammedalhzmi.masrofmanager.util.BranchAssetReportKind
import com.mohammedalhzmi.masrofmanager.util.BranchAssetsPrintAdapter
import java.util.Calendar
import java.util.UUID

private data class EquipmentRow(val document: Document, val value: BranchAssetData.Equipment)
private data class SupplyRow(val document: Document, val value: BranchAssetData.Supply)

@Composable
fun BranchAssetsScreen(
    viewModel: MasrofViewModel,
    onNavigateBack: () -> Unit,
    onAnnualInventory: (String) -> Unit
) {
    val documents by viewModel.allDocuments.collectAsState()
    val equipment = remember(documents) {
        documents.mapNotNull { doc -> BranchAssetData.decodeEquipment(doc)?.let { EquipmentRow(doc, it) } }
    }
    val supplies = remember(documents) {
        documents.mapNotNull { doc -> BranchAssetData.decodeSupply(doc)?.let { SupplyRow(doc, it) } }
    }
    val context = LocalContext.current
    var equipmentDialog by remember { mutableStateOf(false) }
    var supplyDialog by remember { mutableStateOf(false) }
    var editingEquipment by remember { mutableStateOf<Document?>(null) }
    var editingSupply by remember { mutableStateOf<Document?>(null) }

    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = onNavigateBack) { Text("رجوع") }
            Text("ممتلكات الفرع", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        }
        Text("تُحفظ السجلات محليًا أولًا، وتُرسل منفردة إلى قاعدة السحابة عند اعتماد الحساب والجهاز.", style = MaterialTheme.typography.bodySmall)
        Button(onClick = { onAnnualInventory(BranchAssetReportKind.EQUIPMENT.name) }, modifier = Modifier.fillMaxWidth()) {
            Text("الجرد السنوي للمعدات والمستلزمات")
        }

        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("معدات الفرع (${equipment.size})", style = MaterialTheme.typography.titleLarge)
                    Button(onClick = { editingEquipment = null; equipmentDialog = true }) { Text("إضافة معدة") }
                }
                Text("اضغط على صف المعدة لتعديل بياناتها. اسحب الجدول أفقيًا لرؤية جميع الحقول.", style = MaterialTheme.typography.bodySmall)
                EquipmentTable(equipment, onRowClick = { row -> editingEquipment = row.document; equipmentDialog = true })
                OutlinedButton(onClick = { onAnnualInventory(BranchAssetReportKind.EQUIPMENT.name) }, modifier = Modifier.fillMaxWidth()) {
                    Text("جرد سنوي للمعدات")
                }
            }
        }

        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("مستلزمات الفرع (${supplies.size})", style = MaterialTheme.typography.titleLarge)
                    Button(onClick = { editingSupply = null; supplyDialog = true }) { Text("إضافة مستلزم") }
                }
                Text("اضغط على صف المستلزم لتعديل بياناته. اسحب الجدول أفقيًا عند الحاجة.", style = MaterialTheme.typography.bodySmall)
                SupplyTable(supplies) { row -> editingSupply = row.document; supplyDialog = true }
                OutlinedButton(onClick = { onAnnualInventory(BranchAssetReportKind.SUPPLIES.name) }, modifier = Modifier.fillMaxWidth()) {
                    Text("جرد سنوي للمستلزمات")
                }
            }
        }
        Spacer(Modifier.height(6.dp))
    }

    if (equipmentDialog) {
        EquipmentEditorDialog(
            existing = editingEquipment?.let(BranchAssetData::decodeEquipment),
            onDismiss = { equipmentDialog = false; editingEquipment = null },
            onSave = { value, done ->
                val previous = editingEquipment
                val now = System.currentTimeMillis()
                val document = previous?.copy(
                    beneficiaryName = value.name,
                    dateGregorian = value.yearObtained,
                    details = BranchAssetData.encode(value),
                    notes = value.responsiblePerson,
                    updatedAt = now
                ) ?: Document(
                    type = DocumentType.BOOK,
                    documentNumber = "BA-EQ-${UUID.randomUUID()}",
                    dateHijri = "",
                    dateGregorian = value.yearObtained,
                    amount = null,
                    amountWords = null,
                    beneficiaryName = value.name,
                    purpose = "معدة فرع",
                    details = BranchAssetData.encode(value),
                    notes = value.responsiblePerson,
                    status = DocumentStatus.DRAFT,
                    createdAt = now,
                    updatedAt = now,
                    tags = "branch_asset_equipment_v1"
                )
                viewModel.saveBranchAsset(document) { locallySaved, message ->
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    if (locallySaved) { done(); equipmentDialog = false; editingEquipment = null }
                }
            }
        )
    }

    if (supplyDialog) {
        SupplyEditorDialog(
            existing = editingSupply?.let(BranchAssetData::decodeSupply),
            onDismiss = { supplyDialog = false; editingSupply = null },
            onSave = { value, amount, done ->
                val previous = editingSupply
                val now = System.currentTimeMillis()
                val document = previous?.copy(
                    dateGregorian = value.date,
                    amount = amount,
                    beneficiaryName = value.name,
                    purpose = value.kind,
                    details = BranchAssetData.encode(value),
                    updatedAt = now
                ) ?: Document(
                    type = DocumentType.BOOK,
                    documentNumber = "BA-SUP-${UUID.randomUUID()}",
                    dateHijri = "",
                    dateGregorian = value.date,
                    amount = amount,
                    amountWords = null,
                    beneficiaryName = value.name,
                    purpose = value.kind,
                    details = BranchAssetData.encode(value),
                    notes = value.day,
                    status = DocumentStatus.DRAFT,
                    createdAt = now,
                    updatedAt = now,
                    tags = "branch_asset_supply_v1"
                )
                viewModel.saveBranchAsset(document) { locallySaved, message ->
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    if (locallySaved) { done(); supplyDialog = false; editingSupply = null }
                }
            }
        )
    }
}

@Composable
fun BranchAssetsAnnualInventoryScreen(
    viewModel: MasrofViewModel,
    initialKind: String,
    onNavigateBack: () -> Unit
) {
    val documents by viewModel.allDocuments.collectAsState()
    val equipment = remember(documents) { documents.mapNotNull { doc -> BranchAssetData.decodeEquipment(doc)?.let { EquipmentRow(doc, it) } } }
    val supplies = remember(documents) { documents.mapNotNull { doc -> BranchAssetData.decodeSupply(doc)?.let { SupplyRow(doc, it) } } }
    val context = LocalContext.current
    val initialTab = if (initialKind == BranchAssetReportKind.SUPPLIES.name) 1 else 0
    var selectedTab by remember(initialTab) { mutableStateOf(initialTab) }
    var year by remember { mutableStateOf(Calendar.getInstance().get(Calendar.YEAR).toString()) }
    val kind = if (selectedTab == 0) BranchAssetReportKind.EQUIPMENT else BranchAssetReportKind.SUPPLIES
    val title = if (kind == BranchAssetReportKind.EQUIPMENT) "الجرد السنوي لمعدات الفرع" else "الجرد السنوي لمستلزمات الفرع"

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = onNavigateBack) { Text("رجوع") }
            Text("الجرد السنوي", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        }
        Text("نموذج A4 أفقي وفق مرجع الجرد؛ تُسحب السجلات الحالية للفرع دون تغييرها.", style = MaterialTheme.typography.bodySmall)
        TabRow(selectedTabIndex = selectedTab) {
            Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }, text = { Text("المعدات") })
            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }, text = { Text("المستلزمات") })
        }
        OutlinedTextField(
            value = year,
            onValueChange = { year = it.filter(Char::isDigit).take(4) },
            label = { Text("عام الجرد الميلادي") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text("$title للعام: ${year.ifBlank { "...." }} م", style = MaterialTheme.typography.titleMedium)
        Column(Modifier.weight(1f).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            if (selectedTab == 0) EquipmentTable(equipment, onRowClick = null, includeResponsible = false)
            else SupplyTable(supplies, onRowClick = null)
        }
        Button(
            onClick = {
                val printManager = context.getSystemService(PRINT_SERVICE) as PrintManager
                val adapter = BranchAssetsPrintAdapter(context, kind, year.ifBlank { "...." }, equipment.map { it.value }, supplies.map { it.value })
                printManager.print(
                    "جرد ممتلكات الفرع - ${kind.name}",
                    adapter,
                    PrintAttributes.Builder()
                        .setMediaSize(PrintAttributes.MediaSize.ISO_A4.asLandscape())
                        .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
                        .setColorMode(PrintAttributes.COLOR_MODE_COLOR)
                        .build()
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("طباعة / حفظ PDF") }
    }
}

@Composable
private fun EquipmentTable(rows: List<EquipmentRow>, onRowClick: ((EquipmentRow) -> Unit)?, includeResponsible: Boolean = true) {
    val widths = if (includeResponsible) listOf(118.dp, 118.dp, 110.dp, 104.dp, 104.dp, 95.dp, 110.dp, 128.dp)
    else listOf(118.dp, 118.dp, 110.dp, 104.dp, 104.dp, 95.dp, 110.dp)
    val labels = if (includeResponsible) listOf("اسم المعدة", "موديل المعدة", "حالة المعدة", "الرقم الإداري", "رقم المكينة", "رقم القعادة", "سنة الحصول عليها", "المسؤول")
    else listOf("اسم المعدة", "موديل المعدة", "حالة المعدة", "الرقم الإداري", "رقم المكينة", "رقم القعادة", "سنة الحصول عليها")
    Column(Modifier.horizontalScroll(rememberScrollState())) {
        Row {
            labels.forEachIndexed { index, label -> AssetCell(label, widths[index], header = true) }
        }
        if (rows.isEmpty()) {
            Row { labels.indices.forEach { index -> AssetCell(if (index == 0) "لا توجد معدات بعد" else "", widths[index]) } }
        } else rows.forEach { row ->
            Row(Modifier.clickable(enabled = onRowClick != null) { onRowClick?.invoke(row) }) {
                val values = listOf(row.value.name, row.value.model, row.value.condition, row.value.administrativeNumber,
                    row.value.machineNumber, row.value.chassisNumber, row.value.yearObtained) +
                    if (includeResponsible) listOf(row.value.responsiblePerson) else emptyList()
                values.forEachIndexed { index, value -> AssetCell(value, widths[index]) }
            }
        }
    }
}

@Composable
private fun SupplyTable(rows: List<SupplyRow>, onRowClick: ((SupplyRow) -> Unit)?) {
    val widths = listOf(100.dp, 122.dp, 160.dp, 140.dp, 130.dp)
    val labels = listOf("اليوم", "التاريخ", "الاسم", "النوع", "القيمة")
    Column(Modifier.horizontalScroll(rememberScrollState())) {
        Row { labels.forEachIndexed { index, label -> AssetCell(label, widths[index], header = true) } }
        if (rows.isEmpty()) {
            Row { labels.indices.forEach { index -> AssetCell(if (index == 0) "لا توجد مستلزمات بعد" else "", widths[index]) } }
        } else rows.forEach { row ->
            Row(Modifier.clickable(enabled = onRowClick != null) { onRowClick?.invoke(row) }) {
                val values = listOf(row.value.day, row.value.date, row.value.name, row.value.kind, row.value.valueYER)
                values.forEachIndexed { index, value -> AssetCell(value, widths[index]) }
            }
        }
    }
}

@Composable
private fun AssetCell(text: String, width: androidx.compose.ui.unit.Dp, header: Boolean = false) {
    Text(
        text = text,
        modifier = Modifier.width(width).border(0.7.dp, Color.DarkGray)
            .background(if (header) Color(0xFF9BC9F5) else Color.Transparent)
            .padding(horizontal = 5.dp, vertical = 8.dp),
        style = if (header) MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.bodySmall,
        maxLines = if (header) 2 else 3
    )
}

@Composable
private fun EquipmentEditorDialog(
    existing: BranchAssetData.Equipment?,
    onDismiss: () -> Unit,
    onSave: (BranchAssetData.Equipment, () -> Unit) -> Unit
) {
    var name by remember(existing) { mutableStateOf(existing?.name.orEmpty()) }
    var model by remember(existing) { mutableStateOf(existing?.model.orEmpty()) }
    var condition by remember(existing) { mutableStateOf(existing?.condition.orEmpty()) }
    var admin by remember(existing) { mutableStateOf(existing?.administrativeNumber.orEmpty()) }
    var machine by remember(existing) { mutableStateOf(existing?.machineNumber.orEmpty()) }
    var chassis by remember(existing) { mutableStateOf(existing?.chassisNumber.orEmpty()) }
    var year by remember(existing) { mutableStateOf(existing?.yearObtained.orEmpty()) }
    var responsible by remember(existing) { mutableStateOf(existing?.responsiblePerson.orEmpty()) }
    var error by remember { mutableStateOf("") }
    var saving by remember { mutableStateOf(false) }
    AlertDialog(
        onDismissRequest = { if (!saving) onDismiss() },
        title = { Text(if (existing == null) "إضافة معدة" else "تعديل بيانات المعدة") },
        text = {
            Column(Modifier.fillMaxWidth().heightIn(max = 500.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(name, { name = it }, label = { Text("اسم المعدة") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(model, { model = it }, label = { Text("موديل المعدة") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(condition, { condition = it }, label = { Text("حالة المعدة") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(admin, { admin = it }, label = { Text("الرقم الإداري") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(machine, { machine = it }, label = { Text("رقم المكينة") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(chassis, { chassis = it }, label = { Text("رقم القعادة") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(year, { year = it }, label = { Text("سنة الحصول عليها") }, modifier = Modifier.fillMaxWidth(), singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
                OutlinedTextField(responsible, { responsible = it }, label = { Text("المسؤول على المعدة") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                if (error.isNotBlank()) Text(error, color = MaterialTheme.colorScheme.error)
                if (saving) Text("جارٍ الحفظ المحلي ومزامنة السجل...")
            }
        },
        confirmButton = {
            Button(enabled = !saving, onClick = {
                if (name.isBlank()) error = "أدخل اسم المعدة أولًا."
                else {
                    error = ""; saving = true
                    onSave(BranchAssetData.Equipment(name.trim(), model.trim(), condition.trim(), admin.trim(), machine.trim(), chassis.trim(), year.trim(), responsible.trim())) { saving = false }
                }
            }) { Text("حفظ") }
        },
        dismissButton = { TextButton(enabled = !saving, onClick = onDismiss) { Text("إلغاء") } }
    )
}

@Composable
private fun SupplyEditorDialog(
    existing: BranchAssetData.Supply?,
    onDismiss: () -> Unit,
    onSave: (BranchAssetData.Supply, Double, () -> Unit) -> Unit
) {
    var day by remember(existing) { mutableStateOf(existing?.day.orEmpty()) }
    var date by remember(existing) { mutableStateOf(existing?.date.orEmpty()) }
    var name by remember(existing) { mutableStateOf(existing?.name.orEmpty()) }
    var kind by remember(existing) { mutableStateOf(existing?.kind.orEmpty()) }
    var value by remember(existing) { mutableStateOf(existing?.valueYER.orEmpty()) }
    var error by remember { mutableStateOf("") }
    var saving by remember { mutableStateOf(false) }
    AlertDialog(
        onDismissRequest = { if (!saving) onDismiss() },
        title = { Text(if (existing == null) "إضافة مستلزم" else "تعديل بيانات المستلزم") },
        text = {
            Column(Modifier.fillMaxWidth().heightIn(max = 450.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(day, { day = it }, label = { Text("اليوم") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(date, { date = it }, label = { Text("التاريخ") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(name, { name = it }, label = { Text("الاسم") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(kind, { kind = it }, label = { Text("النوع") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(value, { value = it }, label = { Text("القيمة بالريال اليمني") }, modifier = Modifier.fillMaxWidth(), singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal))
                if (error.isNotBlank()) Text(error, color = MaterialTheme.colorScheme.error)
                if (saving) Text("جارٍ الحفظ المحلي ومزامنة السجل...")
            }
        },
        confirmButton = {
            Button(enabled = !saving, onClick = {
                val amount = parseYemeniAmount(value)
                when {
                    name.isBlank() -> error = "أدخل الاسم أولًا."
                    amount == null || amount < 0 -> error = "أدخل قيمة رقمية صحيحة."
                    else -> {
                        error = ""; saving = true
                        onSave(BranchAssetData.Supply(day.trim(), date.trim(), name.trim(), kind.trim(), value.trim()), amount) { saving = false }
                    }
                }
            }) { Text("حفظ") }
        },
        dismissButton = { TextButton(enabled = !saving, onClick = onDismiss) { Text("إلغاء") } }
    )
}

private fun parseYemeniAmount(value: String): Double? {
    val normalized = buildString(value.length) {
        value.forEach { ch ->
            when (ch) {
                in '٠'..'٩' -> append(('0'.code + ch.code - '٠'.code).toChar())
                in '۰'..'۹' -> append(('0'.code + ch.code - '۰'.code).toChar())
                '٫' -> append('.')
                '٬', ',', ' ' -> Unit
                else -> if (ch.isDigit() || ch == '.') append(ch)
            }
        }
    }
    return normalized.toDoubleOrNull()
}
