package com.mohammedalhzmi.masrofmanager.ui.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.border
import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.R

@Composable
fun OfficialFormShell(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(title, style = MaterialTheme.typography.headlineMedium)
        Text("نموذج رسمي مرقم — جميع الحقول تحفظ داخل المستند وتظهر في المعاينة والطباعة", style = MaterialTheme.typography.bodySmall)
        Card(modifier = Modifier.fillMaxWidth().border(1.dp, Color(0xffb48a3a)), colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = Color(0xfffffefd))) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text("صفحة المستند الأصلية — اكتب البيانات مباشرة داخل الحقول", style = MaterialTheme.typography.labelLarge, color = Color(0xff8a651d))
                Spacer(modifier = Modifier.height(6.dp))
                Column(modifier = Modifier.fillMaxWidth().background(Color.White).border(1.dp, Color(0xffe5e7eb)).padding(10.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("NO: ....")
                            Text("التاريخ :   /   /   144 هـ")
                            Text("الموافق :   /   /   2026 م")
                            Text("المرفقات : (         )")
                        }
                        Image(painterResource(R.drawable.official_emblem), "الشعار الرسمي", modifier = Modifier.size(52.dp), contentScale = ContentScale.Fit)
                        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                            Text("الجمهورية اليمنية", style = MaterialTheme.typography.labelSmall)
                            Text("وزارة الإدارة والتنمية المحلية والريفية", style = MaterialTheme.typography.labelSmall, maxLines = 1, softWrap = false)
                            Text("صندوق النظافة والتحسين م/إب", style = MaterialTheme.typography.labelSmall)
                            Text("فرع مديرية الحزم", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(modifier = Modifier.fillMaxWidth().border(1.dp, Color(0xffb48a3a)).padding(8.dp)) { content() }
                }
            }
        }
    }
}

@Composable
fun OfficialField(value: String, label: String, onValueChange: (String) -> Unit, minLines: Int = 1) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        minLines = minLines,
        singleLine = minLines == 1
    )
}

@Composable
fun OfficialTags(value: String, onValueChange: (String) -> Unit) {
    OfficialField(value, "الوسوم — افصل بينها بفاصلة أو اضغط Enter", { input -> onValueChange(input.replace("\n", ",")) })
    Text("وسوم مقترحة: كهرباء، صيانة، رواتب، وقود ومحروقات، قطع غيار", style = MaterialTheme.typography.labelSmall, color = Color(0xff8a651d))
}

@Composable
fun OfficialDates(
    hijri: String, gregorian: String,
    onHijri: (String) -> Unit, onGregorian: (String) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
        OfficialField(hijri, "التاريخ", onHijri, 1)
        OfficialField(gregorian, "الموافق", onGregorian, 1)
    }
}

@Composable
fun SaveOfficialButton(label: String, onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(6.dp))
    Button(onClick = onClick, modifier = Modifier.fillMaxWidth()) { Text(label) }
}
