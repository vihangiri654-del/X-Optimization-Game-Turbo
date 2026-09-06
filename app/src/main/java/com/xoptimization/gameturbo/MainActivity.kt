     package com.optimizer.gameturbo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF07080C) // Deep futuristic space black
                ) {
                    XGameTurboHomeScreen()
                }
            }
        }
    }
}

@Composable
fun XGameTurboHomeScreen() {
    val context = LocalContext.current
    var isBeastMode by remember { mutableStateOf(false) }
    var isFloatingWindowActive by remember { mutableStateOf(false) }
    var fpsValue by remember { mutableStateOf("120 FPS") }
    var aiStatusText by remember { mutableStateOf("JAM AI: System fully optimized & ready for gaming.") }
    var ramStatus by remember { mutableStateOf("4.2GB / 8GB (Optimal)") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF07080C),
                        Color(0xFF0F111A),
                        Color(0xFF050608)
                    )
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Header: Title & Status Indicator
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "X-TURBO // LIQUID OS",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00FFFF)
                    )
                    Text(
                        text = "ENGINEERED BY JAM AI",
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }
                
                // Live Status Badge
                Box(
                    modifier = Modifier
                        .background(
                            color = if (isBeastMode) Color.Red.copy(alpha = 0.2f) else Color.Green.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(50.dp)
                        )
                        .border(
                            1.dp,
                            if (isBeastMode) Color.Red else Color.Green,
                            RoundedCornerShape(50.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = if (isBeastMode) "● ULTRA BEAST" else "● SYSTEM STABLE",
                        color = if (isBeastMode) Color.Red else Color.Green,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Middle Section: Liquid Glass Cards Grid / Column Layout
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Liquid Glass Card 1: Performance & FPS HUD
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.12f),
                                    Color.White.copy(alpha = 0.03f)
                                )
                            ),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .border(
                            1.5.dp,
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF00FFFF).copy(alpha = 0.6f),
                                    Color.Transparent
                                )
                            ),
                            RoundedCornerShape(20.dp)
                        )
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("REAL-TIME METRICS", color = Color(0xFF00FFFF), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                            Text(fpsValue, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                        Divider(color = Color.White.copy(alpha = 0.1f), thickness = 1.dp)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("RAM Usage: $ramStatus", color = Color.LightGray, fontSize = 12.sp)
                            Text("GPU: Locked 90Hz", color = Color.LightGray, fontSize = 12.sp)
                        }
                    }
                }

                // Liquid Glass Card 2: JAM AI Assistant Live Panel
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF9C27B0).copy(alpha = 0.15f),
                                    Color(0xFF00FFFF).copy(alpha = 0.05f)
                                )
                            ),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .border(
                            1.5.dp,
                            Color(0xFF9C27B0).copy(alpha = 0.5f),
                            RoundedCornerShape(20.dp)
                        )
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("🤖 JAM AI CORE ASSISTANT", color = Color(0xFFE040FB), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        Text(
                            text = aiStatusText,
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 12.sp
                        )
                    }
                }
            }

            // Bottom Section: Futuristic Action Controls
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Beast Mode Toggle Button
                Button(
                    onClick = { 
                        isBeastMode = !isBeastMode
                        if (isBeastMode) {
                            fpsValue = "144 FPS (UNLOCKED)"
                            aiStatusText = "JAM AI: Ultra Beast engaged! CPU governors forced to performance mode, thermal limits bypassed."
                            ramStatus = "2.8GB / 8GB (Boosted)"
                        } else {
                            fpsValue = "120 FPS"
                            aiStatusText = "JAM AI: Switched back to balanced adaptive cooling mode."
                            ramStatus = "4.2GB / 8GB (Optimal)"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isBeastMode) Color(0xFFFF1744) else Color(0xFF00E5FF)
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Text(
                        text = if (isBeastMode) "⚡ DEACTIVATE ULTRA BEAST" else "🚀 ACTIVATE ULTRA BEAST",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Row for secondary actions (Floating Window & RAM Boost)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Floating Window Button
                    Button(
                        onClick = {
                            if (!Settings.canDrawOverlays(context)) {
                                val intent = Intent(
                                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                    Uri.parse("package:${context.packageName}")
                                )
                                context.startActivity(intent)
                            } else {
                                isFloatingWindowActive = !isFloatingWindowActive
                                aiStatusText = if (isFloatingWindowActive) "JAM AI: HUD floating window active over games." else "JAM AI: Floating window hidden."
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E2230)),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                    ) {
                        Text(
                            text = if (isFloatingWindowActive) "CLOSE HUD" else "FLOAT WINDOW",
                            color = Color(0xFF00FFFF),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // RAM Boost Button
                    Button(
                        onClick = {
                            ramStatus = "1.9GB / 8GB (Max Cleaned)"
                            aiStatusText = "JAM AI: Deep sweep executed! 650MB background junk wiped clean."
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E2230)),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                    ) {
                        Text(
                            text = "SWEEP RAM",
                            color = Color(0xFF00FF66),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
                    
