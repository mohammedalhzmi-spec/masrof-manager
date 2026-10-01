package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync
import kotlinx.coroutines.launch

@Composable
fun ManagerDashboardScreen(onOpenFinance: () -> Unit) {
    val scope = rememberCoroutineScope()
    var requests by remember { mutableStateOf<List<FirebaseCloudSync.DeviceRequest>>(emptyList()) }
    var message by remember { mutableStateOf<String?>(null) }
    fun refresh() { scope.launch { runCatching { requests = FirebaseCloudSync.pendingDeviceRequests() }.onFailure { message = it.message } } }
    LaunchedEffect(Unit) { refresh() }
    val navy = Color(0xff123b5d); val green = Color(0xff16834f)
    Column(Modifier.fillMaxSize().background(Color(0xffeef3f5)).padding(16.dp)) {
        Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(navy)) {
            Column(Modifier.padding(18.dp)) {
                Text("بوابة مدير النظام", color = Color.White, fontSize = 23.sp, fontWeight = FontWeight.Bold)
                Text("إدارة المستخدمين والأجهزة والاعتمادات", color = Color(0xffdce9ef), fontSize = 13.sp)
                Text("مدير النظام — صندوق النظافة والتحسين م/إب", color = Color(0xffc8e6d3), fontSize = 12.sp)
            }
        }
        Spacer(Modifier.height(12.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Card(Modifier.weight(1f), colors = CardDefaults.cardColors(Color.White)) { Column(Modifier.padding(14.dp)) { Text("طلبات الأجهزة", color = Color(0xff607080), fontSize = 12.sp); Text(requests.size.toString(), color = navy, fontSize = 28.sp, fontWeight = FontWeight.Bold) } }
            Card(Modifier.weight(1f), colors = CardDefaults.cardColors(Color.White)) { Column(Modifier.padding(14.dp)) { Text("الحماية", color = Color(0xff607080), fontSize = 12.sp); Text("مفعّلة", color = green, fontSize = 18.sp, fontWeight = FontWeight.Bold) } }
        }
        Spacer(Modifier.height(12.dp))
        Text("طلبات اعتماد الأجهزة", color = navy, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(6.dp))
        if (message != null) Text(message.orEmpty(), color = Color(0xffa12a22), fontSize = 12.sp)
        if (requests.isEmpty()) Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(Color.White)) { Text("لا توجد طلبات معلقة حاليًا", color = Color(0xff607080), modifier = Modifier.padding(18.dp)) }
        LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(requests, key = { it.id }) { request ->
                Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(Color.White)) {
                    Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text(request.email, color = navy, fontWeight = FontWeight.Bold)
                        Text("الجهاز: ${request.deviceName}", color = Color(0xff607080), fontSize = 12.sp)
                        Text("معرف الجهاز: ${request.deviceId}", color = Color(0xff607080), fontSize = 11.sp)
                        Button(onClick = { scope.launch { runCatching { FirebaseCloudSync.approveDevice(request); requests = requests.filterNot { it.id == request.id }; message = "تم اعتماد الجهاز وإرسال إشعار للمستخدم" }.onFailure { message = it.message } } }, colors = ButtonDefaults.buttonColors(containerColor = green), modifier = Modifier.fillMaxWidth()) { Text("اعتماد الجهاز") }
                    }
                }
            }
        }
        OutlinedButton(onClick = { refresh() }, modifier = Modifier.fillMaxWidth()) { Text("تحديث الطلبات") }
        Spacer(Modifier.height(6.dp))
        Button(onClick = onOpenFinance, colors = ButtonDefaults.buttonColors(containerColor = navy), modifier = Modifier.fillMaxWidth()) { Text("فتح النظام المالي") }
    }
}
