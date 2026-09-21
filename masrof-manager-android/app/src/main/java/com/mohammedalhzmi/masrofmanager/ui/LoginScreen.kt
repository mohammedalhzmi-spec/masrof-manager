package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(onLogin: (String, String, Boolean) -> Unit, onRegister: () -> Unit, error: String?) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var remember by remember { mutableStateOf(true) }
    val fieldColors = OutlinedTextFieldDefaults.colors(unfocusedTextColor = MaterialTheme.colorScheme.onSurface, focusedTextColor = MaterialTheme.colorScheme.onSurface)
    val pulse = rememberInfiniteTransition(label = "login-artwork").animateFloat(1f, 1.025f, infiniteRepeatable(tween(2200), RepeatMode.Reverse), label = "cover-pulse")
    var showText by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { delay(350); showText = true }
    Box(Modifier.fillMaxSize().background(Color(0xfff5f6f8))) {
        Column(Modifier.fillMaxSize().verticalScroll(androidx.compose.foundation.rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(R.drawable.developer_welcome), "غلاف نظام المالية", Modifier.fillMaxWidth().height(360.dp).graphicsLayer { scaleX = pulse.value; scaleY = pulse.value }.clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)), contentScale = ContentScale.Crop)
            AnimatedVisibility(showText, enter = fadeIn(tween(700)) + slideInVertically(initialOffsetY = { it / 3 })) {
                Column(Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 18.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    TypewriterText("تسجيل الدخول", style = MaterialTheme.typography.headlineMedium, color = Color(0xff102035), charDelay = 95L)
                    TypewriterText("أدخل بيانات حسابك للوصول إلى النظام", style = MaterialTheme.typography.bodyMedium, color = Color(0xff6b7280), charDelay = 38L)
                    Spacer(Modifier.height(14.dp))
                    OutlinedTextField(username, { username = it }, label = { Text("اسم المستخدم") }, colors = fieldColors, modifier = Modifier.fillMaxWidth(), singleLine = true)
                    OutlinedTextField(password, { password = it }, label = { Text("كلمة المرور") }, colors = fieldColors, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth(), singleLine = true)
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) { Checkbox(remember, { remember = it }); Text("تذكر تسجيل الدخول على هذا الجهاز", fontSize = 12.sp) }
                    if (error != null) Text(error, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(8.dp))
                    Button(onClick = { onLogin(username, password, remember) }, enabled = username.isNotBlank() && password.isNotBlank(), modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) { Text("دخول") }
                    OutlinedButton(onClick = onRegister, modifier = Modifier.fillMaxWidth()) { Text("تسجيل مستخدم جديد") }
                    Spacer(Modifier.height(12.dp))
                    TypewriterText("هذا النظام من برمجة وتطوير المطور محمد الحزمي", style = MaterialTheme.typography.bodySmall, color = Color(0xff9a6b00), charDelay = 32L)
                }
            }
        }
    }
}

@Composable
private fun TypewriterText(text: String, style: androidx.compose.ui.text.TextStyle, color: Color, charDelay: Long) {
    var visible by remember(text) { mutableStateOf("") }
    LaunchedEffect(text) {
        visible = ""
        text.forEachIndexed { index, _ ->
            delay(charDelay)
            visible = text.take(index + 1)
        }
    }
    Text(visible + if (visible.length < text.length) "▌" else "", style = style, color = color)
}
