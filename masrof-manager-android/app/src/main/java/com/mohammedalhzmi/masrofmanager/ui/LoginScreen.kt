package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val GovNavy = Color(0xff123b5d)
private val GovGreen = Color(0xff16834f)
private val GovGold = Color(0xffb48a32)
private val LoginBackground = Color(0xffeef3f5)
private const val DeveloperCredit = "تم برمجة وتطوير هذا النظام بواسطة المطور محمد الحزمي 2026"

@Composable
fun LoginScreen(onLogin: (String, String, Boolean) -> Unit, onRegister: () -> Unit, error: String?) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var remember by remember { mutableStateOf(true) }
    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = GovNavy,
        unfocusedTextColor = GovNavy,
        focusedBorderColor = GovGreen,
        unfocusedBorderColor = Color(0xffb9c6d0),
        focusedLabelColor = GovGreen,
        unfocusedLabelColor = Color(0xff607080)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LoginBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(4.dp))
        Text("الجمهورية اليمنية", fontSize = 27.sp, fontWeight = FontWeight.Bold, color = GovNavy)
        Text("صندوق النظافة والتحسين م/إب", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = GovNavy)
        Text("فرع مديرية الحزم", fontSize = 21.sp, fontWeight = FontWeight.Bold, color = GovGreen)

        Spacer(Modifier.height(18.dp))
        HorizontalDivider(modifier = Modifier.fillMaxWidth(0.72f), thickness = 2.dp, color = GovGold)
        Spacer(Modifier.height(18.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 22.dp, vertical = 28.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "دخول المستخدمين",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = GovNavy,
                    textAlign = TextAlign.Center
                )
                Text(
                    "الدخول اللاحق يتم باسم المستخدم وكلمة المرور",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 13.sp,
                    color = Color(0xff607080),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(6.dp))
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("اسم المستخدم") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = "اسم المستخدم") },
                    colors = fieldColors,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("كلمة المرور") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "كلمة المرور") },
                    colors = fieldColors,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = remember,
                        onCheckedChange = { remember = it },
                        colors = CheckboxDefaults.colors(checkedColor = GovGreen)
                    )
                    Text("تذكر الحساب على هذا الجهاز", fontSize = 12.sp, color = GovNavy)
                }
                if (error != null) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xfffff1f0))
                    ) {
                        Text(
                            error,
                            modifier = Modifier.padding(10.dp),
                            fontSize = 12.sp,
                            color = Color(0xffa12a22)
                        )
                    }
                }
                Button(
                    onClick = { onLogin(username, password, remember) },
                    enabled = username.isNotBlank() && password.isNotBlank(),
                    colors = ButtonDefaults.buttonColors(containerColor = GovGreen),
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ) {
                    Text("دخول آمن", fontWeight = FontWeight.Bold)
                }
                TextButton(onClick = { /* ستربط باستعادة Firebase في المسار التالي */ }) {
                    Text("نسيت كلمة المرور؟ إرسال رابط استعادة", color = GovNavy, fontSize = 14.sp)
                }
                Text(
                    "الدخول لأول مرة بالبريد الإلكتروني",
                    color = GovNavy,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                OutlinedButton(
                    onClick = onRegister,
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    Text("طلب إنشاء حساب مستخدم", color = GovNavy)
                }
            }
        }

        Spacer(Modifier.height(32.dp))
        Text(
            "منظومة داخلية لإدارة المستندات المالية وحركة الاعتماد",
            fontSize = 11.sp,
            color = Color(0xff647482),
            textAlign = TextAlign.Center
        )
        Text(
            "النظام المالي الخاص بفرع صندوق النظافة والتحسين مديرية الحزم",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = GovGold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(18.dp))
        Text(
            DeveloperCredit,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = GovNavy,
            textAlign = TextAlign.Center
        )
    }
}
