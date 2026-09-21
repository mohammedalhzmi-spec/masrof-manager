package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.mohammedalhzmi.masrofmanager.util.AppRole

@Composable
fun RegisterScreen(onRegister: (String, String, String, AppRole, Boolean, Boolean) -> Unit, onBack: () -> Unit, error: String?) {
    var username by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var role by remember { mutableStateOf(AppRole.USER) }
    var agreeStorage by remember { mutableStateOf(false) }
    var agreeTerms by remember { mutableStateOf(false) }
    val fieldColors = OutlinedTextFieldDefaults.colors(unfocusedTextColor = MaterialTheme.colorScheme.onSurface, focusedTextColor = MaterialTheme.colorScheme.onSurface)
    Column(modifier = Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("تسجيل مستخدم جديد", style = MaterialTheme.typography.headlineMedium)
        Text("أدخل البيانات ثم اختر الدور المناسب قبل حفظ الحساب.", style = MaterialTheme.typography.bodySmall)
        OutlinedTextField(username, { username = it }, label = { Text("اسم المستخدم") }, colors = fieldColors, modifier = Modifier.fillMaxWidth(), singleLine = true)
        OutlinedTextField(fullName, { fullName = it }, label = { Text("الاسم الكامل") }, colors = fieldColors, modifier = Modifier.fillMaxWidth(), singleLine = true)
        OutlinedTextField(password, { password = it }, label = { Text("كلمة المرور") }, colors = fieldColors, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth(), singleLine = true)
        OutlinedTextField(confirm, { confirm = it }, label = { Text("تأكيد كلمة المرور") }, colors = fieldColors, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth(), singleLine = true)
        Text("دور المستخدم", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.fillMaxWidth()) { AppRole.values().forEach { item -> if (item == role) Button(onClick = { role = item }, modifier = Modifier.weight(1f)) { Text(item.title, maxLines = 1) } else OutlinedButton(onClick = { role = item }, modifier = Modifier.weight(1f)) { Text(item.title, maxLines = 1) } } }
        Row(verticalAlignment = Alignment.CenterVertically) { Checkbox(agreeStorage, { agreeStorage = it }); Text("أوافق على حفظ بيانات الحساب محليًا داخل التطبيق") }
        Row(verticalAlignment = Alignment.CenterVertically) { Checkbox(agreeTerms, { agreeTerms = it }); Text("أوافق على الشروط وسياسة الاستخدام والخصوصية") }
        if (error != null) Text(error, color = MaterialTheme.colorScheme.error)
        Button(enabled = username.isNotBlank() && fullName.isNotBlank() && password.length >= 6 && password == confirm && agreeStorage && agreeTerms, onClick = { onRegister(username, password, fullName, role, agreeStorage, agreeTerms) }, modifier = Modifier.fillMaxWidth()) { Text("الموافقة على الحفظ وإنشاء الحساب") }
        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text("رجوع لتسجيل الدخول") }
    }
}
