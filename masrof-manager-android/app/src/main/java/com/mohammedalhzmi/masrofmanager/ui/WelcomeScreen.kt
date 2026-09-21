package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(onFinished: () -> Unit) {
    var progress by remember { mutableFloatStateOf(0f) }
    val animatedProgress by animateFloatAsState(progress, tween(500), label = "welcome-progress")
    LaunchedEffect(Unit) {
        for (step in 1..10) { delay(180); progress = step / 10f }
        delay(650); onFinished()
    }
    Box(Modifier.fillMaxSize().background(Color(0xfff7faf7)), contentAlignment = Alignment.Center) {
        Card(Modifier.fillMaxWidth().padding(24.dp), shape = RoundedCornerShape(24.dp), colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(8.dp)) {
            Column(Modifier.fillMaxWidth().padding(22.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(R.drawable.developer_welcome), "غلاف نظام المالية الرسمي", Modifier.fillMaxWidth().height(300.dp).clip(RoundedCornerShape(16.dp)), contentScale = ContentScale.Crop)
                Text("نظام المالية لصندوق النظافة الحزم", style = MaterialTheme.typography.headlineSmall, color = Color(0xff173b25))
                Text("نظام مالي وإداري متكامل", style = MaterialTheme.typography.bodyMedium, color = Color(0xff6b7280))
                Spacer(Modifier.height(22.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) { Text("عداد الدخول الآمن", fontSize = 12.sp, color = Color(0xff4b5563)); Text("${(animatedProgress * 100).toInt()}%", fontSize = 12.sp, color = Color(0xff16794b)) }
                Spacer(Modifier.height(6.dp))
                LinearProgressIndicator(animatedProgress, Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)), color = Color(0xff16834f), trackColor = Color(0xffdcefe4))
                Spacer(Modifier.height(20.dp))
                Text("هذا النظام من برمجة وتطوير المطور محمد الحزمي", fontSize = 11.sp, color = Color(0xff9a6b00))
                Text("جميع الحقوق محفوظة © 2026", fontSize = 10.sp, color = Color(0xff6b7280))
            }
        }
    }
}
