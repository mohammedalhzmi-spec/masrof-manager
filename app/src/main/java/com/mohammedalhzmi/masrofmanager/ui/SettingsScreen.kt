package com.mohammedalhzmi.masrofmanager.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.util.AppPreferences

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    var ministry by remember { mutableStateOf(AppPreferences.ministry(context)) }
    var administration by remember { mutableStateOf(AppPreferences.administration(context)) }
    var branch by remember { mutableStateOf(AppPreferences.branch(context)) }
    var manager by remember { mutableStateOf(AppPreferences.manager(context)) }
    var finance by remember { mutableStateOf(AppPreferences.financeManager(context)) }
    var orderLogo by remember { mutableStateOf(AppPreferences.logoUri(context, DocumentType.ORDER)) }
    var requestLogo by remember { mutableStateOf(AppPreferences.logoUri(context, DocumentType.REQUEST)) }
    var receiptLogo by remember { mutableStateOf(AppPreferences.logoUri(context, DocumentType.RECEIPT)) }
    val orderPicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri -> uri?.let { orderLogo = it.toString(); AppPreferences.saveLogo(context, DocumentType.ORDER, it) } }
    val requestPicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri -> uri?.let { requestLogo = it.toString(); AppPreferences.saveLogo(context, DocumentType.REQUEST, it) } }
    val receiptPicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri -> uri?.let { receiptLogo = it.toString(); AppPreferences.saveLogo(context, DocumentType.RECEIPT, it) } }
    Column(modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("إعدادات النماذج الرسمية", style = MaterialTheme.typography.headlineMedium)
        Text("يتم حفظ البيانات والشعارات محليًا وتطبيقها على النوع المحدد فقط.", style = MaterialTheme.typography.bodySmall)
        SettingField("الوزارة", ministry) { ministry = it }
        SettingField("الإدارة / الصندوق", administration) { administration = it }
        SettingField("الفرع / المديرية", branch) { branch = it }
        SettingField("اسم مدير الفرع", manager) { manager = it }
        SettingField("اسم المدير المالي", finance) { finance = it }
        LogoSetting("شعار أمر الصرف", orderLogo != null, { orderPicker.launch("image/*") })
        LogoSetting("شعار ورقة التقديم", requestLogo != null, { requestPicker.launch("image/*") })
        LogoSetting("شعار ورقة الاستلام", receiptLogo != null, { receiptPicker.launch("image/*") })
        Button(onClick = {
            AppPreferences.put(context, "ministry", ministry); AppPreferences.put(context, "administration", administration); AppPreferences.put(context, "branch", branch); AppPreferences.put(context, "manager", manager); AppPreferences.put(context, "finance_manager", finance); onBack()
        }, modifier = Modifier.fillMaxWidth()) { Text("حفظ الإعدادات") }
        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text("رجوع") }
    }
}

@Composable
private fun SettingField(label: String, value: String, onChange: (String) -> Unit) { OutlinedTextField(value, onChange, label = { Text(label) }, modifier = Modifier.fillMaxWidth()) }

@Composable
private fun LogoSetting(label: String, selected: Boolean, onPick: () -> Unit) {
    OutlinedButton(onClick = onPick, modifier = Modifier.fillMaxWidth()) { Text(if (selected) "$label — تم اختيار شعار" else "$label — اختيار صورة") }
}
