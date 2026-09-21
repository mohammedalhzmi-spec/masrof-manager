package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import com.mohammedalhzmi.masrofmanager.util.AppLockPreferences
import com.mohammedalhzmi.masrofmanager.util.LockType

@Composable
fun AppLockScreen(type: LockType, biometricAvailable: Boolean, onBiometric: () -> Unit, onUnlock: (String) -> Boolean) {
    var secret by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("نظام مالية صندوق النظافة", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))
        Text("التطبيق مقفل لحماية المستندات والبيانات المالية")
        Spacer(Modifier.height(20.dp))
        when (type) {
            LockType.PATTERN -> PatternPad(secret) { secret += it }
            else -> OutlinedTextField(
                value = secret,
                onValueChange = { secret = it; error = false },
                label = { Text(if (type == LockType.PIN) "الرمز الرقمي" else "كلمة المرور") },
                visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = if (type == LockType.PIN) KeyboardType.NumberPassword else KeyboardType.Password),
                singleLine = true
            )
        }
        if (error) Text("بيانات القفل غير صحيحة", color = MaterialTheme.colorScheme.error)
        Spacer(Modifier.height(12.dp))
        Button(onClick = { if (onUnlock(secret)) error = false else error = true }, modifier = Modifier.fillMaxWidth()) { Text("فتح التطبيق") }
        if (biometricAvailable) {
            OutlinedButton(onClick = onBiometric, modifier = Modifier.fillMaxWidth()) { Text("فتح بالبصمة / أمان الهاتف") }
        }
        if (type == LockType.PATTERN && secret.isNotEmpty()) TextButton(onClick = { secret = "" }) { Text("مسح النقش") }
    }
}

@Composable
private fun PatternPad(value: String, onTap: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(if (value.isEmpty()) "ارسم نقشًا من 4 نقاط على الأقل" else "النقاط: ${value.length}")
        for (row in 0..2) {
            Row {
                for (column in 0..2) {
                    val number = row * 3 + column + 1
                    OutlinedButton(onClick = { if (!value.contains(number.toString())) onTap(number.toString()) }, modifier = Modifier.padding(4.dp).size(72.dp)) { Text(number.toString()) }
                }
            }
        }
    }
}
