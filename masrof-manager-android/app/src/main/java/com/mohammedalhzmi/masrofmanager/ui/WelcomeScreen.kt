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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import kotlinx.coroutines.delay

private val WelcomeBackground = Color(0xffeef3f5)
private val GovNavy = Color(0xff16486d)
private val GovGreen = Color(0xff198b5b)
private val GovGold = Color(0xffad8631)

@Composable
fun WelcomeScreen(onFinished: () -> Unit) {
    var progress by remember { mutableFloatStateOf(0f) }
    val animatedProgress by animateFloatAsState(progress, tween(850), label = "welcome-progress")

    LaunchedEffect(Unit) {
        for (step in 1..10) {
            delay(240)
            progress = step / 10f
        }
        delay(650)
        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WelcomeBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp, vertical = 26.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.weight(0.95f))

            Image(
                painter = painterResource(R.drawable.official_emblem),
                contentDescription = "شعار الجمهورية اليمنية",
                modifier = Modifier
                    .width(270.dp)
                    .height(154.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.height(18.dp))
            Text("الجمهورية اليمنية", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = GovNavy)
            Text("صندوق النظافة والتحسين", fontSize = 23.sp, fontWeight = FontWeight.Bold, color = GovNavy)
            Text("فرع مديرية الحزم", fontSize = 21.sp, fontWeight = FontWeight.Bold, color = GovGreen)

            Spacer(Modifier.height(48.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 7.dp)
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("نظام المالية والإدارة", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = GovNavy)
                    Spacer(Modifier.height(6.dp))
                    Text("منصة المستندات والاعتمادات المالية", fontSize = 15.sp, color = Color(0xff737b80), textAlign = TextAlign.Center)
                    Spacer(Modifier.height(34.dp))
                    LinearProgressIndicator(
                        progress = { animatedProgress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        color = GovGreen,
                        trackColor = Color(0xffdce9e3)
                    )
                    Spacer(Modifier.height(24.dp))
                    Text("جاري تهيئة بيئة العمل الآمنة", fontSize = 14.sp, color = Color(0xff747b80))
                }
            }

            Spacer(Modifier.height(56.dp))
            Text(
                "النظام المالي الخاص بفرع صندوق النظافة والتحسين مديرية الحزم",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = GovGold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(18.dp))
            Text(
                "تم برمجة وتطوير هذا النظام بواسطة المطور محمد الحزمي 2026",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = GovNavy,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.weight(0.55f))
        }
    }
}
