package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohammedalhzmi.masrofmanager.data.UserEntity
import com.mohammedalhzmi.masrofmanager.util.AppRole
import com.mohammedalhzmi.masrofmanager.util.UserSession
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun UserManagementScreen(viewModel: MasrofViewModel, onBack: () -> Unit) {
    val users by viewModel.allUsers.collectAsState()
    val logs by viewModel.auditLogs.collectAsState()
    var editing by remember { mutableStateOf<UserEntity?>(null) }
    var username by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var role by remember { mutableStateOf(AppRole.USER) }
    var active by remember { mutableStateOf(true) }
    fun load(user: UserEntity?) { editing = user; username = user?.username.orEmpty(); fullName = user?.fullName.orEmpty(); password = ""; role = user?.let { runCatching { AppRole.valueOf(it.role) }.getOrDefault(AppRole.USER) } ?: AppRole.USER; active = user?.active ?: true }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("إدارة المستخدمين", style = MaterialTheme.typography.headlineMedium); TextButton(onClick = onBack) { Text("رجوع") } }
        Text("أنشئ حسابات مستقلة، حدّد الدور، وأوقف الحساب عند الحاجة.", style = MaterialTheme.typography.bodySmall)
        OutlinedTextField(username, { username = it }, label = { Text("اسم المستخدم") }, modifier = Modifier.fillMaxWidth(), singleLine = true, enabled = editing == null)
        OutlinedTextField(fullName, { fullName = it }, label = { Text("الاسم الكامل") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        OutlinedTextField(password, { password = it }, label = { Text(if (editing == null) "كلمة المرور" else "كلمة مرور جديدة (اختياري)") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { AppRole.values().forEach { item -> if (item == role) Button(onClick = { role = item }) { Text(item.title) } else OutlinedButton(onClick = { role = item }) { Text(item.title) } } }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("الحساب نشط"); Switch(active, { active = it }) }
        Button(enabled = username.isNotBlank() && fullName.isNotBlank() && (editing != null || password.length >= 6), onClick = {
            if (editing == null) viewModel.createUser(username, password, fullName, role.name) else viewModel.updateUser(editing!!, password.ifBlank { null }, fullName, role.name, active)
            load(null)
        }, modifier = Modifier.fillMaxWidth()) { Text(if (editing == null) "إنشاء الحساب" else "حفظ التعديلات") }
        if (editing != null) OutlinedButton(onClick = { load(null) }, modifier = Modifier.fillMaxWidth()) { Text("إلغاء التعديل") }
        Text("الحسابات", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 10.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(users, key = { it.id }) { user ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)) { Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(modifier = Modifier.weight(1f)) { Text("${user.fullName} — ${user.username}"); Text("${runCatching { AppRole.valueOf(user.role).title }.getOrDefault(user.role)} — ${if (user.active) "نشط" else "موقوف"}", style = MaterialTheme.typography.bodySmall) }
                    TextButton(onClick = { load(user) }) { Text("تعديل") }
                    if (user.id != UserSession.current?.id) TextButton(onClick = { viewModel.deleteUser(user) }) { Text("حذف") }
                } }
            }
        }
        Text("سجل العمليات", style = MaterialTheme.typography.titleLarge)
        LazyColumn(modifier = Modifier.heightIn(max = 180.dp)) { items(logs.take(20), key = { it.id }) { log -> Text("${date(log.timestamp)} — ${log.username}: ${log.action} — ${log.details}", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(vertical = 2.dp)) } }
    }
}

private fun date(value: Long) = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(value))
