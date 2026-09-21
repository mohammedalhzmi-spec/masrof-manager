package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(onFinished: () -> Unit) {
    LaunchedEffect(Unit) { delay(2800); onFinished() }
    Box(modifier = Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(R.drawable.developer_welcome), contentDescription = "شاشة المطور محمد الحزمي", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        Text("جاري تجهيز التطبيق...", color = Color.White, fontSize = 14.sp, modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 28.dp))
    }
}
