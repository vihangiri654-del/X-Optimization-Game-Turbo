 package com.optimizer.gameturbo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF0D0E15)
                ) {
                    XGameTurboHomeScreen()
                }
            }
        }
    }
}

@Composable
fun XGameTurboHomeScreen() {
    var isBeastMode by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D0E15)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "X OPTIMIZATION",
                fontSize = 28.sp,
                color = Color.Cyan
            )
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.15f),
                                Color.White.copy(alpha = 0.05f)
                            )
                        ),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .border(
                        1.5.dp,
                        if (isBeastMode) Color.Red else Color.Cyan.copy(alpha = 0.5f),
                        RoundedCornerShape(24.dp)
                    )
                    .padding(20.dp)
            ) {
                Column {
                    Text("JAM AI SYSTEM: ACTIVE", color = Color.White, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = if (isBeastMode) "MODE: ULTRA BEAST" else "MODE: ALL BALANCED",
                        color = if (isBeastMode) Color.Red else Color.Green,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Real FPS: Measuring...", color = Color.Gray, fontSize = 12.sp)
                }
            }

            Button(
                onClick = { isBeastMode = !isBeastMode },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isBeastMode) Color.Red else Color(0xFF00E5FF)
                ),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier.fillMaxWidth().height(55.dp)
            ) {
                Text(
                    text = if (isBeastMode) "DEACTIVATE BEAST MODE" else "ACTIVATE ULTRA BEAST",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }
    }
}
                       
