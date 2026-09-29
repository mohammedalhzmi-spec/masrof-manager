package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R

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
    val pulse = rememberInfiniteTransition(label = "emblem-breath").animateFloat(
        1f,
        1.035f,
        infiniteRepeatable(tween(2200), RepeatMode.Reverse),
        label = "emblem-scale"
    )
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
        Spacer(Modifier.height(12.dp))
        Image(
            painter = painterResource(R.drawable.official_emblem),
            contentDescription = "شعار الجمهورية اليمنية",
            modifier = Modifier.size(112.dp).graphicsLayer { scaleX = pulse.value; scaleY = pulse.value },
            contentScale = ContentScale.Fit
        )
        Spacer(Modifier.height(8.dp))
        Text("الجمهورية اليمنية", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = GovNavy)
        Text("صندوق النظافة والتحسين م/إب", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = GovNavy)
        Text("فرع مديرية الحزم", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = GovGreen)

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
                modifier = Modifier.fillMaxWidth().padding(22.dp),
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
                    colors = fieldColors,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("كلمة المرور") },
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
