package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.mohammedalhzmi.masrofmanager.util.UpdateCenter

@Composable
fun UpdateCenterScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var status by remember { mutableStateOf("لم يتم فحص التحديثات بعد") }
    var availableUrl by remember { mutableStateOf<String?>(null) }
    Column(modifier = Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("مركز التحديثات", style = MaterialTheme.typography.headlineMedium)
        Text("يمكن للتطبيق فحص إصدار منشور وفتح صفحة التحديث. تحديث كود Android يتطلب تنزيل APK وتثبيته وفق حماية النظام.", style = MaterialTheme.typography.bodySmall)
        Button(onClick = {
            status = "جارٍ الفحص..."
            scope.launch {
                val info = UpdateCenter.check()
                if (info == null) status = "تعذر الاتصال بمصدر التحديثات"
                else if (info.versionCode > 1) { status = "يتوفر إصدار ${info.versionName}: ${info.notes}"; availableUrl = info.apkUrl }
                else status = "التطبيق محدث حاليًا — الإصدار 1.0"
            }
        }, modifier = Modifier.fillMaxWidth()) { Text("فحص التحديثات") }
        Text(status)
        if (availableUrl != null) Button(onClick = { UpdateCenter.openRelease(context, availableUrl!!) }, modifier = Modifier.fillMaxWidth()) { Text("فتح التحديث") }
        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text("رجوع") }
    }
}
