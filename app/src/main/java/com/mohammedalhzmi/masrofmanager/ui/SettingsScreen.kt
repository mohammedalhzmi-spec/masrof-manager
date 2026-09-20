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
import com.mohammedalhzmi.masrofmanager.util.AppLockPreferences
import com.mohammedalhzmi.masrofmanager.util.LockType
import com.mohammedalhzmi.masrofmanager.util.AppRole
import com.mohammedalhzmi.masrofmanager.util.RolePreferences

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
    var lockEnabled by remember { mutableStateOf(AppLockPreferences.enabled(context)) }
    var biometricEnabled by remember { mutableStateOf(AppLockPreferences.biometricEnabled(context)) }
    var lockType by remember { mutableStateOf(AppLockPreferences.type(context)) }
    var newSecret by remember { mutableStateOf("") }
    var timeout by remember { mutableStateOf(AppLockPreferences.timeoutMinutes(context).toString()) }
    var userName by remember { mutableStateOf(RolePreferences.userName(context)) }
    var selectedRole by remember { mutableStateOf(RolePreferences.currentRole(context)) }
    val orderPicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri -> uri?.let { orderLogo = it.toString(); AppPreferences.saveLogo(context, DocumentType.ORDER, it) } }
    val requestPicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri -> uri?.let { requestLogo = it.toString(); AppPreferences.saveLogo(context, DocumentType.REQUEST, it) } }
    val receiptPicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri -> uri?.let { receiptLogo = it.toString(); AppPreferences.saveLogo(context, DocumentType.RECEIPT, it) } }
    Column(modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("إعدادات النماذج الرسمية", style = MaterialTheme.typography.headlineMedium)
        Text("يتم حفظ البيانات والشعارات محليًا وتطبيقها على النوع المحدد فقط.", style = MaterialTheme.typography.bodySmall)
        Text("المستخدم والصلاحيات", style = MaterialTheme.typography.titleLarge)
        SettingField("اسم المستخدم الحالي", userName) { userName = it }
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.fillMaxWidth()) {
            AppRole.values().forEach { role ->
                if (role == selectedRole) Button(onClick = { selectedRole = role }, modifier = Modifier.weight(1f)) { Text(role.title, maxLines = 1) }
                else OutlinedButton(onClick = { selectedRole = role }, modifier = Modifier.weight(1f)) { Text(role.title, maxLines = 1) }
            }
        }
        Text("الدور يحدد إنشاء المستندات والتعديل والحذف والطباعة والنسخ الاحتياطي والإعدادات.", style = MaterialTheme.typography.bodySmall)
        SettingField("الوزارة", ministry) { ministry = it }
        SettingField("الإدارة / الصندوق", administration) { administration = it }
        SettingField("الفرع / المديرية", branch) { branch = it }
        SettingField("اسم مدير الفرع", manager) { manager = it }
        SettingField("اسم المدير المالي", finance) { finance = it }
        LogoSetting("شعار أمر الصرف", orderLogo != null, { orderPicker.launch("image/*") })
        LogoSetting("شعار ورقة التقديم", requestLogo != null, { requestPicker.launch("image/*") })
        LogoSetting("شعار ورقة الاستلام", receiptLogo != null, { receiptPicker.launch("image/*") })
        HorizontalDivider()
        Text("أمان التطبيق", style = MaterialTheme.typography.titleLarge)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("قفل التطبيق عند الخروج أو انتهاء المهلة")
            Switch(checked = lockEnabled, onCheckedChange = { lockEnabled = it })
        }
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            LockTypeButton("أرقام", LockType.PIN, lockType) { lockType = it }
            LockTypeButton("كلمة", LockType.PASSWORD, lockType) { lockType = it }
            LockTypeButton("نقش", LockType.PATTERN, lockType) { lockType = it }
        }
        SettingField(if (lockType == LockType.PIN) "رمز جديد" else if (lockType == LockType.PASSWORD) "كلمة مرور جديدة" else "نقش جديد (أرقام النقاط مثل 1478)", newSecret) { newSecret = it }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("السماح بالبصمة / أمان الهاتف")
            Switch(checked = biometricEnabled, onCheckedChange = { biometricEnabled = it })
        }
        SettingField("مهلة إعادة القفل بالدقائق (0 = فورًا)", timeout) { timeout = it.filter(Char::isDigit) }
        Text("اقتراحات أمان: استخدم بصمة الهاتف، ورمزًا لا يقل عن 4 أرقام، ولا تشارك كلمة المرور.", style = MaterialTheme.typography.bodySmall)
        Button(enabled = !lockEnabled || AppLockPreferences.hasSecret(context) || newSecret.isNotBlank(), onClick = {
            AppPreferences.put(context, "ministry", ministry); AppPreferences.put(context, "administration", administration); AppPreferences.put(context, "branch", branch); AppPreferences.put(context, "manager", manager); AppPreferences.put(context, "finance_manager", finance)
            RolePreferences.setUserName(context, userName); RolePreferences.setRole(context, selectedRole)
            AppLockPreferences.setEnabled(context, lockEnabled); AppLockPreferences.setBiometricEnabled(context, biometricEnabled); AppLockPreferences.setType(context, lockType); AppLockPreferences.setTimeoutMinutes(context, timeout.toIntOrNull() ?: 5); if (newSecret.isNotBlank()) AppLockPreferences.setSecret(context, newSecret)
            onBack()
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

@Composable
private fun LockTypeButton(label: String, type: LockType, selected: LockType, onSelect: (LockType) -> Unit) {
    if (type == selected) Button(onClick = { onSelect(type) }) { Text(label) } else OutlinedButton(onClick = { onSelect(type) }) { Text(label) }
}
