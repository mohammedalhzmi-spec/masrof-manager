package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(onLogin: (String, String, Boolean) -> Unit, onRegister: () -> Unit, error: String?) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var remember by remember { mutableStateOf(true) }
    val fieldColors = OutlinedTextFieldDefaults.colors(unfocusedTextColor = MaterialTheme.colorScheme.onSurface, focusedTextColor = MaterialTheme.colorScheme.onSurface)
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("تسجيل الدخول", style = MaterialTheme.typography.headlineMedium)
        Text("أدخل بيانات حسابك للوصول إلى النظام", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(username, { username = it }, label = { Text("اسم المستخدم") }, colors = fieldColors, modifier = Modifier.fillMaxWidth(), singleLine = true)
        OutlinedTextField(password, { password = it }, label = { Text("كلمة المرور") }, colors = fieldColors, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth(), singleLine = true)
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) { Checkbox(remember, { remember = it }); Text("تذكر تسجيل الدخول على هذا الجهاز") }
        if (error != null) Text(error, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(8.dp))
        Button(onClick = { onLogin(username, password, remember) }, enabled = username.isNotBlank() && password.isNotBlank(), modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) { Text("دخول") }
        OutlinedButton(onClick = onRegister, modifier = Modifier.fillMaxWidth()) { Text("تسجيل مستخدم جديد") }
        Spacer(Modifier.height(12.dp))
        Text("الحساب الأولي: admin / admin1234", style = MaterialTheme.typography.bodySmall)
    }
}
