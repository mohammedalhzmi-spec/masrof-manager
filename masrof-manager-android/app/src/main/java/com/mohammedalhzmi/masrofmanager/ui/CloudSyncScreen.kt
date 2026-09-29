package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.mohammedalhzmi.masrofmanager.cloud.CloudGate

@Composable
fun CloudSyncScreen(viewModel: MasrofViewModel, onBack: () -> Unit) {
    val state by viewModel.cloudAccessState.collectAsState()
    val busy by viewModel.cloudBusy.collectAsState()
    val operationMessage by viewModel.cloudOperationMessage.collectAsState()
    val pendingDevices by viewModel.pendingCloudDevices.collectAsState()
    var identifier by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(Unit) { viewModel.refreshCloudAccess() }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("حساب Firebase والمزامنة", style = MaterialTheme.typography.headlineMedium)
        Text(
            "المزامنة يدوية وتدمج سجلات السحابة مع قاعدة Room المحلية؛ لا تحذف مستندات محلية. يتم التحقق من اسم المستخدم داخل الخدمة الآمنة ولا يُكشف البريد المرتبط للهاتف.",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            "قد يلزم اعتماد هذا الجهاز مرة واحدة بعد تفعيل حماية مفاتيح الأجهزة.",
            style = MaterialTheme.typography.bodySmall
        )
        HorizontalDivider()
        Text(state.message, style = MaterialTheme.typography.bodyMedium)

        if (!state.signedIn) {
            OutlinedTextField(
                value = identifier,
                onValueChange = { identifier = it },
                label = { Text("البريد الإلكتروني أو اسم المستخدم السحابي") },
                singleLine = true,
                enabled = !busy,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("كلمة مرور Firebase") },
                singleLine = true,
                enabled = !busy,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    viewModel.signInToCloud(identifier.trim(), password)
                    password = ""
                },
                enabled = !busy && identifier.isNotBlank() && password.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) { Text("تسجيل الدخول إلى السحابة") }
            Text("هذه شاشة دخول للحساب القائم فقط؛ لا تنشئ حسابًا جديدًا ولا ترفع بيانات الدخول إلى المستودع.", style = MaterialTheme.typography.bodySmall)
        } else {
            Text("الحساب: ${state.email.orEmpty()}", style = MaterialTheme.typography.bodyMedium)
            Text("الدور السحابي: ${state.role.orEmpty()}", style = MaterialTheme.typography.bodySmall)

            if (state.gate == CloudGate.DEVICE_PENDING) {
                OutlinedButton(
                    onClick = viewModel::requestCloudDeviceApproval,
                    enabled = !busy,
                    modifier = Modifier.fillMaxWidth()
                ) { Text("إعادة فحص / طلب اعتماد هذا الجهاز") }
            }

            if (state.canSync) {
                Button(
                    onClick = viewModel::syncCloudDocuments,
                    enabled = !busy,
                    modifier = Modifier.fillMaxWidth()
                ) { Text("مزامنة المستندات الآن") }
            }

            if (state.canManageDevices) {
                OutlinedButton(
                    onClick = viewModel::loadPendingCloudDevices,
                    enabled = !busy,
                    modifier = Modifier.fillMaxWidth()
                ) { Text("عرض طلبات اعتماد الأجهزة") }
                pendingDevices.forEach { request ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(request.email.ifBlank { request.userId }, style = MaterialTheme.typography.titleSmall)
                            Text(request.deviceName, style = MaterialTheme.typography.bodySmall)
                            Text("معرّف الجهاز: ${request.deviceId}", style = MaterialTheme.typography.bodySmall)
                            OutlinedButton(
                                onClick = { viewModel.approveCloudDevice(request) },
                                enabled = !busy,
                                modifier = Modifier.fillMaxWidth()
                            ) { Text("اعتماد الجهاز") }
                        }
                    }
                }
            }

            OutlinedButton(
                onClick = viewModel::signOutFromCloud,
                enabled = !busy,
                modifier = Modifier.fillMaxWidth()
            ) { Text("تسجيل الخروج من حساب السحابة") }
        }

        if (busy) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                CircularProgressIndicator()
            }
        }
        if (operationMessage.isNotBlank()) Text(operationMessage, style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text("رجوع") }
    }
}
