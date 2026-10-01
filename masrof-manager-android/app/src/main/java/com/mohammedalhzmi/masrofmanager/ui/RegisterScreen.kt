package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.activity.compose.BackHandler
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val navy = Color(0xff123b5d); val green = Color(0xff16834f)
    BackHandler(onBack = onBack)
    val fieldColors = OutlinedTextFieldDefaults.colors(focusedBorderColor = green, focusedLabelColor = green, unfocusedBorderColor = Color(0xffb9c6d0))
    Box(Modifier.fillMaxSize().background(Color(0xffeef3f5))) {
        Column(Modifier.fillMaxSize().padding(18.dp).verticalScroll(androidx.compose.foundation.rememberScrollState()), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text("طلب إنشاء حساب مستخدم", style = MaterialTheme.typography.headlineSmall, color = navy, fontWeight = FontWeight.Bold)
            Text("يخضع الحساب للصلاحيات المعتمدة في منظومة الفرع.", color = Color(0xff607080), fontSize = 13.sp)
            Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(Color.White)) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                    OutlinedTextField(username, { username = it }, label = { Text("البريد الإلكتروني أو اسم المستخدم") }, supportingText = { Text("يمكن استخدام بريد حقيقي أو اسم مستخدم داخلي") }, colors = fieldColors, modifier = Modifier.fillMaxWidth(), singleLine = true)
                    OutlinedTextField(fullName, { fullName = it }, label = { Text("الاسم الرباعي") }, colors = fieldColors, modifier = Modifier.fillMaxWidth(), singleLine = true)
                    OutlinedTextField(password, { password = it }, label = { Text("كلمة المرور") }, colors = fieldColors, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth(), singleLine = true)
                    OutlinedTextField(confirm, { confirm = it }, label = { Text("تأكيد كلمة المرور") }, colors = fieldColors, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth(), singleLine = true)
                    Text("الدور الوظيفي المطلوب", color = navy, fontWeight = FontWeight.Bold)
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) { AppRole.values().forEach { item -> if (item == role) Button(onClick = { role = item }, colors = ButtonDefaults.buttonColors(containerColor = green), modifier = Modifier.fillMaxWidth()) { Text(item.title) } else OutlinedButton(onClick = { role = item }, modifier = Modifier.fillMaxWidth()) { Text(item.title, color = navy) } } }
                    Row(verticalAlignment = Alignment.CenterVertically) { Checkbox(agreeStorage, { agreeStorage = it }, colors = CheckboxDefaults.colors(checkedColor = green)); Text("أوافق على حفظ بيانات الحساب محليًا", fontSize = 12.sp, color = navy) }
                    Row(verticalAlignment = Alignment.CenterVertically) { Checkbox(agreeTerms, { agreeTerms = it }, colors = CheckboxDefaults.colors(checkedColor = green)); Text("أوافق على الشروط وسياسة الاستخدام", fontSize = 12.sp, color = navy) }
                    if (error != null) Card(colors = CardDefaults.cardColors(Color(0xfffff1f0))) { Text(error, color = Color(0xffa12a22), fontSize = 12.sp, modifier = Modifier.padding(10.dp)) }
                    Button(enabled = username.isNotBlank() && fullName.isNotBlank() && password.length >= 6 && password == confirm && agreeStorage && agreeTerms, onClick = { onRegister(username, password, fullName, role, agreeStorage, agreeTerms) }, colors = ButtonDefaults.buttonColors(containerColor = green), modifier = Modifier.fillMaxWidth().height(50.dp)) { Text("إرسال طلب إنشاء الحساب", fontWeight = FontWeight.Bold) }
                    OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text("العودة إلى الدخول", color = navy) }
                }
            }
        }
    }
}
