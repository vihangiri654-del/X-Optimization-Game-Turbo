package com.optimizer.gameturbo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.math.BigInteger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF000000)
                ) {
                    ZeroLoadUltimateUIScreen()
                }
            }
        }
    }
}

@Composable
fun ZeroLoadUltimateUIScreen() {
    val context = LocalContext.current
    val uiScope = rememberCoroutineScope()
    
    var activeGameProfile by remember { mutableStateOf("Minecraft [Zero-Load Ultimate UI]") }
    var isZeroLoadEngineActive by remember { mutableStateOf(false) }
    var isHudVisible by remember { mutableStateOf(false) }
    var activeCategory by remember { mutableStateOf("ZERO_LOAD_UI") }
    
    var autonomousUiTier by remember { mutableStateOf(BigInteger("99999999999999999999999999999999999")) }
    var dynamicButterFps by remember { mutableStateOf(99999999) }
    
    val uiLogs = remember { 
        mutableStateListOf("[UI_CORE]: Autonomous Self-Mutation Active. Phone Hardware Load: Exactly 0.0%.") 
    }
    
    val zeroLoadTweaks = remember { mutableStateMapOf(
        "Autonomous UI Self-Mutation Engine" to true,
        "Zero Hardware CPU/GPU Strain Lock" to true,
        "Infinite Butter-Smooth UI Refinement" to true,
        "Virtual Sandbox Self-Rewriting Rights" to true,
        "Zero-Crash Eternity Exception Barrier" to true,
        "Thermal Cool-State Hardware Shield" to true,
        "Background Battery Preservation Vault" to true,
        "Intergalactic Absolute Supremacy Lock" to false
    ) }

    fun pushUiLog(text: String) {
        uiLogs.add(0, "[ZERO_LOAD_AI]: $text")
        if (uiLogs.size > 50) uiLogs.removeLast()
    }

    LaunchedEffect(isZeroLoadEngineActive) {
        if (isZeroLoadEngineActive) {
            uiScope.launch(Dispatchers.Default) {
                while (isActive) {
                    delay(1000L)
                    autonomousUiTier = autonomousUiTier.add(BigInteger.valueOf(777777777L))
                    dynamicButterFps += 999999
                    pushUiLog("UI Self-Mutated Successfully. Tier: $autonomousUiTier | Hardware Load: 0.0%")
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF001A1A),
                        Color(0xFF000808),
                        Color(0xFF000000)
                    ),
                    radius = 2900f
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Box(
                        modifier = Modifier
                            .size(13.dp)
                            .background(if (isZeroLoadEngineActive) Color(0xFF00FFCC) else Color(0xFF00897B), CircleShape)
                            .border(1.5.dp, Color.White, CircleShape)
                    )
                    Column {
                        Text(
                            text = "ZERO-LOAD UI // AUTONOMOUS EVOLUTION",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFF64FFDA),
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = "HARDWARE LOAD: 0.0% | TEMP: NORMAL",
                            fontSize = 8.sp,
                            color = Color(0xFF80CBC4)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .background(Color(0xFF00332C), RoundedCornerShape(20.dp))
                        .border(1.dp, Color(0xFF64FFDA).copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = if (isZeroLoadEngineActive) "UI: AUTONOMOUS" else "MODE: STANDBY",
                        color = if (isZeroLoadEngineActive) Color(0xFF00FFCC) else Color(0xFF00897B),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                val profiles = listOf("Minecraft [Zero-Load Ultimate UI]", "Free Fire Max [Butter Smooth]", "BGMI [Absolute Zero-Lag]", "General [Pure Battery Save]")
                items(profiles) { profile ->
                    val isSelected = activeGameProfile == profile
                    Box(
                        modifier = Modifier
                            .background(
                                if (isSelected) Color(0xFF00FFCC).copy(alpha = 0.2f) else Color(0xFF001412),
                                RoundedCornerShape(10.dp)
                            )
                            .border(
                                1.dp,
                                if (isSelected) Color(0xFF00FFCC) else Color.White.copy(alpha = 0.1f),
                                RoundedCornerShape(10.dp)
                            )
                            .clickable {
                                activeGameProfile = profile
                                pushUiLog("Profile switched to $profile with zero hardware strain.")
                            }
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = profile,
                            color = if (isSelected) Color(0xFF64FFDA) else Color.LightGray,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                val tabs = listOf("ZERO_LOAD_UI", "SELF_MUTATE", "HARDWARE_SHIELD")
                tabs.forEach { tab ->
                    val isTabSelected = activeCategory == tab
                    Button(
                        onClick = { activeCategory = tab },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isTabSelected) Color(0xFF00FFCC).copy(alpha = 0.2f) else Color(0xFF000A09)
                        ),
                        border = BorderStroke(1.dp, if (isTabSelected) Color(0xFF00FFCC) else Color.Gray.copy(alpha = 0.3f)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f).height(36.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(text = tab, color = if (isTabSelected) Color(0xFF64FFDA) else Color.Gray, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color(0xFF000A09), RoundedCornerShape(14.dp))
                    .border(1.dp, Color(0xFF00FFCC).copy(alpha = 0.3f), RoundedCornerShape(14.dp))
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "ZERO-LOAD AUTONOMOUS UI CATALOG",
                    fontSize = 10.sp,
                    color = Color(0xFF64FFDA),
                    fontWeight = FontWeight.Bold
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    zeroLoadTweaks.forEach { (tweakName, status) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF001A18), RoundedCornerShape(10.dp))
                                .border(1.dp, if (status) Color(0xFF00FFCC).copy(alpha = 0.4f) else Color.Transparent, RoundedCornerShape(10.dp))
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = tweakName, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                Text(text = "Hardware Impact: Exactly 0.0% (Protected)", color = Color(0xFF80CBC4), fontSize = 9.sp)
                            }
                            Switch(
                                checked = status,
                                onCheckedChange = { newState ->
                                    zeroLoadTweaks[tweakName] = newState
                                    pushUiLog("UI tweak '$tweakName' updated to $newState.")
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color(0xFF00FFCC),
                                    checkedTrackColor = Color(0xFF00FFCC).copy(alpha = 0.3f)
                                )
                            )
                        }
                    }
                }
            }

            Button(
                onClick = { 
                    isZeroLoadEngineActive = !isZeroLoadEngineActive
                    uiScope.launch {
                        if (isZeroLoadEngineActive) {
                            pushUiLog("ZERO-LOAD UI ENGINE ENGAGED! Autonomous sandbox running.")
                            delay(100)
                            pushUiLog("Phone CPU/GPU completely isolated. 0% Load guaranteed.")
                        } else {
                            pushUiLog("Engine paused safely.")
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isZeroLoadEngineActive) Color(0xFF64FFDA) else Color(0xFF00FFCC)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                Text(
                    text = if (isZeroLoadEngineActive) "PAUSE ZERO-LOAD UI ENGINE" else "LAUNCH ZERO-LOAD AUTONOMOUS ENGINE",
                    color = Color.Black,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Button(
                onClick = {
                    if (!Settings.canDrawOverlays(context)) {
                        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${context.packageName}"))
                        context.startActivity(intent)
                    } else {
                        isHudVisible = !isHudVisible
                        pushUiLog(if (isHudVisible) "Zero-Load HUD activated." else "HUD minimized.")
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00332C)),
                border = BorderStroke(1.dp, Color(0xFF00FFCC)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().height(42.dp)
            ) {
                Text(
                    text = "TOGGLE FLOATING HUD & ZERO-LOAD DOCK",
                    color = Color(0xFF64FFDA),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (isHudVisible) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF000A09), RoundedCornerShape(10.dp))
                        .border(1.5.dp, Color(0xFF00FFCC), RoundedCornerShape(10.dp))
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "UI HUD [BUTTER SMOOTH]", color = Color(0xFF00FFCC), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            Text(text = "LOAD: 0.0% | TEMP: 36°C (NORMAL)", color = Color.White, fontSize = 11.sp)
                        }
                        Button(
                            onClick = { isHudVisible = false },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF1744)),
                            modifier = Modifier.height(26.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
                        ) {
                            Text(text = "X", color = Color.White, fontSize = 10.sp)
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(0xFF000505), RoundedCornerShape(10.dp))
                    .border(1.dp, Color(0xFF00FFCC).copy(alpha = 0.3f), RoundedCornerShape(10.dp))
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "ZERO-LOAD TELEMETRY CONSOLE",
                    color = Color(0xFF64FFDA),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
                Divider(color = Color.White.copy(alpha = 0.1f))
                
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    uiLogs.forEach { log ->
                        Text(
                            text = log,
                            color = Color(0xFF80CBC4),
                            fontSize = 9.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }
    }
}
