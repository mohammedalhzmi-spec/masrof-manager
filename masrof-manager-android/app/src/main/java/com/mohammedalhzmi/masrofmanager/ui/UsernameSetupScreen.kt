package com.mohammedalhzmi.masrofmanager.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UsernameSetupScreen(onConfirm: (String) -> Unit, onBack: () -> Unit, error: String?) {
    var username by remember { mutableStateOf("") }
    val navy = Color(0xff123b5d)
    val green = Color(0xff16834f)
    BackHandler(onBack = onBack)
    Box(Modifier.fillMaxSize().background(Color(0xffeef3f5)), contentAlignment = Alignment.Center) {
        Card(Modifier.fillMaxWidth().padding(20.dp), shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(Color.White)) {
            Column(Modifier.padding(22.dp), verticalArrangement = Arrangement.spacedBy(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("إكمال إعداد الحساب", color = navy, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text("تم إنشاء الحساب بالبريد بنجاح. اختر اسم المستخدم الذي ستستعمله في الدخول القادم.", color = Color(0xff607080), fontSize = 13.sp)
                OutlinedTextField(username, { username = it }, label = { Text("اسم المستخدم") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                if (error != null) Text(error, color = Color(0xffa12a22), fontSize = 12.sp)
                Button(onClick = { onConfirm(username.trim()) }, enabled = username.trim().length >= 3, colors = ButtonDefaults.buttonColors(containerColor = green), modifier = Modifier.fillMaxWidth()) { Text("حفظ اسم المستخدم والدخول") }
                OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text("رجوع") }
            }
        }
    }
}
