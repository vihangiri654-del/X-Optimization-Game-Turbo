package com.optimizer.gameturbo

import android.app.Service
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.provider.Settings
import android.view.Gravity
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
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
                    color = Color(0xFF000208)
                ) {
                    QuantumPhysicsEngineApp()
                }
            }
        }
    }
}

@Composable
fun QuantumPhysicsEngineApp() {
    var showSplash by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(1500L)
        showSplash = false
    }

    Box(modifier = Modifier.fillMaxSize()) {
        QuantumDashboard()

        AnimatedVisibility(
            visible = showSplash,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            colors = listOf(Color(0xFF00FFCC).copy(alpha = 0.5f), Color(0xFF0044FF).copy(alpha = 0.3f), Color(0xFF000208)),
                            radius = 2600f
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color(0xFF050B14).copy(alpha = 0.8f), CircleShape)
                            .border(2.dp, Color(0xFF00FFCC), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color(0xFF00E5FF).copy(alpha = 0.9f), CircleShape)
                        )
                    }
                    Text(
                        text = "INITIALIZING QUANTUM PHYSICS ENGINE...",
                        color = Color(0xFF00FFCC),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace
                    )
                    LinearProgressIndicator(
                        modifier = Modifier.width(200.dp).height(2.dp),
                        color = Color(0xFF00E5FF),
                        trackColor = Color(0xFF0A1128)
                    )
                }
            }
        }
    }
}

@Composable
fun QuantumDashboard() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var activeShortcut by remember { mutableStateOf("Quantum") }
    val myGameLibrary = remember { mutableStateListOf("Free Fire MAX", "Minecraft", "BGMI", "Call of Duty") }
    var selectedGame by remember { mutableStateOf("Free Fire MAX") }
    var isQuantumOverclocked by remember { mutableStateOf(true) }
    var showAddDialog by remember { mutableStateOf(false) }

    var cpuPower by remember { mutableStateOf(1.0f) }
    var gpuPower by remember { mutableStateOf(1.0f) }
    var quantumFlux by remember { mutableStateOf(BigInteger("9999999999999999999999999")) }

    val matrixLogs = remember {
        mutableStateListOf(
            "[QUANTUM_CORE]: Matrix initialized with zero-point energy fields.",
            "[HUD_SYSTEM]: Liquid Glass side panels fully compiled."
        )
    }

    fun pushLog(text: String) {
        matrixLogs.add(0, "[PHYSICS]: $text")
        if (matrixLogs.size > 30) matrixLogs.removeLast()
    }

    LaunchedEffect(isQuantumOverclocked) {
        if (isQuantumOverclocked) {
            scope.launch(Dispatchers.Default) {
                while (isActive) {
                    delay(800L)
                    quantumFlux = quantumFlux.add(BigInteger.valueOf(777777777L))
                    pushLog("Quantum Flux Stabilized at 9.99 THz.")
                }
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFF000208), Color(0xFF040C1A), Color(0xFF000104))
                )
            )
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // --- 1. LEFT SHORTCUT DOCK ---
        Column(
            modifier = Modifier
                .width(100.dp)
                .fillMaxHeight()
                .background(Color(0xFF08101E).copy(alpha = 0.7f), RoundedCornerShape(16.dp))
                .border(1.dp, Color(0xFF00FFCC).copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "QUANTUM",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF00FFCC),
                    fontFamily = FontFamily.Monospace
                )
                Spacer(modifier = Modifier.height(2.dp))

                val shortcuts = listOf("Quantum", "Physics", "Flux", "Core", "Shield", "FPS+", "Cloud")
                shortcuts.forEach { shortcut ->
                    val isSelected = activeShortcut == shortcut
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                if (isSelected) Color(0xFF00FFCC).copy(alpha = 0.25f) else Color.Transparent,
                                RoundedCornerShape(8.dp)
                            )
                            .border(
                                1.dp,
                                if (isSelected) Color(0xFF00FFCC) else Color(0xFF00FFCC).copy(alpha = 0.1f),
                                RoundedCornerShape(8.dp)
                            )
                            .clickable {
                                activeShortcut = shortcut
                                pushLog("Switched Matrix Protocol: $shortcut")
                            }
                            .padding(vertical = 6.dp, horizontal = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = shortcut,
                            color = if (isSelected) Color(0xFF00FFCC) else Color(0xFF8CA0C0),
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF00FFCC).copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier.size(7.dp).background(Color(0xFF00FFCC), CircleShape))
                }
                Text(text = "Suman", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
            }
        }

        // --- 2. CENTER MAIN LIQUID GLASS HUD ---
        Column(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF08101E).copy(alpha = 0.7f), RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFF00E5FF).copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Quantum Nexus: Suman Giri", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(text = "Physics Engine Active • Liquid Glass UI", fontSize = 8.sp, color = Color(0xFF8CA0C0))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = "Library: ${myGameLibrary.size}", fontSize = 8.sp, color = Color(0xFF00FFCC), fontWeight = FontWeight.Bold)
                    Text(text = "FPS: 120Hz", fontSize = 8.sp, color = Color(0xFF00E5FF), fontWeight = FontWeight.Bold)
                }
            }

            // Games Matrix Row with Add Game Option
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF08101E).copy(alpha = 0.7f), RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFF102A45), RoundedCornerShape(12.dp))
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "LIQUID GAMES LIBRARY",
                        fontSize = 9.sp,
                        color = Color(0xFF00FFCC),
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                    
                    Button(
                        onClick = { showAddDialog = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00FFCC).copy(alpha = 0.2f)),
                        border = BorderStroke(1.dp, Color(0xFF00FFCC)),
                        modifier = Modifier.height(24.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
                    ) {
                        Text(text = "+ ADD GAMES", color = Color(0xFF00FFCC), fontSize = 8.sp, fontWeight = FontWeight.Bold)
                    }
                }

                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(myGameLibrary) { game ->
                        val isSelected = selectedGame == game
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .background(if (isSelected) Color(0xFF00FFCC).copy(alpha = 0.2f) else Color(0xFF02060D), RoundedCornerShape(8.dp))
                                .border(1.dp, if (isSelected) Color(0xFF00FFCC) else Color(0xFF102A45), RoundedCornerShape(8.dp))
                                .clickable {
                                    selectedGame = game
                                    pushLog("Loaded $game into Quantum Field.")
                                }
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = game, color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                                Spacer(modifier = Modifier.height(4.dp))
                                Button(
                                    onClick = { selectedGame = game },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00FFCC)),
                                    modifier = Modifier.height(20.dp),
                                    contentPadding = PaddingValues(horizontal = 6.dp, vertical = 0.dp)
                                ) {
                                    Text(text = "PLAY", color = Color.Black, fontSize = 7.sp, fontWeight = FontWeight.Black)
                                }
                            }
                        }
                    }
                }
            }

            // Quantum Physics Overclock Control
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF08101E).copy(alpha = 0.7f), RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFF00FFCC).copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "QUANTUM PHYSICS FLUX [9.99 THz]",
                        fontSize = 9.sp,
                        color = Color(0xFF00FFCC),
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                    Switch(
                        checked = isQuantumOverclocked,
                        onCheckedChange = {
                            isQuantumOverclocked = it
                            pushLog(if (it) "Quantum Physics Field Engaged." else "Matrix Standby.")
                        },
                        colors = SwitchDefaults.colors(checkedThumbColor = Color.Black, checkedTrackColor = Color(0xFF00FFCC))
                    )
                }
                Text(
                    text = "Zero-point thermal dissipation active",
                    fontSize = 8.sp,
                    color = Color(0xFF8CA0C0)
                )
            }

            // Live Physics Telemetry Log Console
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xFF010408), RoundedCornerShape(10.dp))
                    .border(1.dp, Color(0xFF102A45), RoundedCornerShape(10.dp))
                    .padding(6.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(text = "QUANTUM PHYSICS TELEMETRY", color = Color(0xFF00FFCC), fontSize = 7.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
                Divider(color = Color(0xFF102A45))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(1.dp)
                ) {
                    matrixLogs.forEach { log ->
                        Text(text = log, color = Color(0xFF8CA0C0), fontSize = 7.sp, fontFamily = FontFamily.Monospace)
                    }
                }
            }
        }

        // --- 3. RIGHT QUANTUM CONTROL PANEL ---
        Column(
            modifier = Modifier
                .width(190.dp)
                .fillMaxHeight()
                .background(Color(0xFF08101E).copy(alpha = 0.7f), RoundedCornerShape(16.dp))
                .border(1.dp, Color(0xFF00FFCC).copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "MATRIX CONTROLS",
                fontSize = 10.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFF00FFCC),
                fontFamily = FontFamily.Monospace
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                listOf("Liquid", "Matrix", "Void").forEach { mode ->
                    Button(
                        onClick = { pushLog("Protocol Shift: $mode") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (mode == "Liquid") Color(0xFF00FFCC).copy(alpha = 0.25f) else Color(0xFF02060D)
                        ),
                        modifier = Modifier.weight(1f).height(24.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(text = mode, color = Color.White, fontSize = 7.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(text = "CPU Core Flux: ${(cpuPower * 100).toInt()}%", fontSize = 8.sp, color = Color.White)
                Slider(
                    value = cpuPower,
                    onValueChange = { cpuPower = it },
                    colors = SliderDefaults.colors(thumbColor = Color(0xFF00FFCC), activeTrackColor = Color(0xFF00FFCC))
                )

                Text(text = "GPU Matrix: ${(gpuPower * 100).toInt()}%", fontSize = 8.sp, color = Color.White)
                Slider(
                    value = gpuPower,
                    onValueChange = { gpuPower = it },
                    colors = SliderDefaults.colors(thumbColor = Color(0xFF00E5FF), activeTrackColor = Color(0xFF00E5FF))
                )
            }

            Text(
                text = "IN-GAME FLOATING HUD",
                fontSize = 8.sp,
                color = Color(0xFF00FFCC),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )

            Button(
                onClick = {
                    if (!Settings.canDrawOverlays(context)) {
                        val intent = Intent(
                            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:${context.packageName}")
                        )
                        context.startActivity(intent)
                        pushLog("Requesting Overlay Access...")
                    } else {
                        val serviceIntent = Intent(context, QuantumPhysicsOverlayService::class.java)
                        context.startService(serviceIntent)
                        pushLog("In-Game Floating Liquid HUD Projected Successfully!")
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF02060D)),
                border = BorderStroke(1.dp, Color(0xFF00FFCC)),
                modifier = Modifier.fillMaxWidth().height(32.dp)
            ) {
                Text(
                    text = if (!Settings.canDrawOverlays(context)) "GRANT PERMISSION" else "LAUNCH IN-GAME HUD",
                    color = Color(0xFF00FFCC),
                    fontSize = 7.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    // --- ADD GAME DIALOG ---
    if (showAddDialog) {
        val packageManager = context.packageManager
        val installedApps = remember {
            packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
                .filter { (it.flags and ApplicationInfo.FLAG_SYSTEM) == 0 }
                .map { packageManager.getApplicationLabel(it).toString() }
                .distinct()
                .sorted()
        }

        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            containerColor = Color(0xFF08101E),
            shape = RoundedCornerShape(16.dp),
            title = {
                Text(
                    text = "ADD GAME TO LIQUID LIBRARY",
                    color = Color(0xFF00FFCC),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.Monospace
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                ) {
                    Text(
                        text = "Choose any app to integrate into Quantum Engine:",
                        color = Color(0xFF8CA0C0),
                        fontSize = 9.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(installedApps) { appName ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFF02060D), RoundedCornerShape(8.dp))
                                    .border(1.dp, Color(0xFF00FFCC).copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                                    .clickable {
                                        if (!myGameLibrary.contains(appName)) {
                                            myGameLibrary.add(appName)
                                            pushLog("Added '$appName' to Quantum Library!")
                                        }
                                        showAddDialog = false
                                    }
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = appName,
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { showAddDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00FFCC))
                ) {
                    Text(text = "CLOSE", color = Color.Black, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                }
            }
        )
    }
}

// --- FLOATING IN-GAME SIDE PANEL SERVICE ---
class QuantumPhysicsOverlayService : Service() {
    private var windowManager: WindowManager? = null
    private var floatingView: android.view.View? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        
        val composeView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(object : androidx.lifecycle.LifecycleOwner {
                private val lifecycleRegistry = androidx.lifecycle.LifecycleRegistry(this)
                init { lifecycleRegistry.handleLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_RESUME) }
                override fun getLifecycle(): androidx.lifecycle.Lifecycle = lifecycleRegistry
            })
            setViewTreeSavedStateRegistryOwner(object : androidx.savedstate.SavedStateRegistryOwner {
                override fun getSavedStateRegistry() = androidx.savedstate.SavedStateRegistry(this)
                override fun getLifecycle() = androidx.lifecycle.LifecycleRegistry(this)
            })

            setContent {
                MaterialTheme {
                    Box(
                        modifier = Modifier
                            .width(210.dp)
                            .height(250.dp)
                            .background(Color(0xFF08101E).copy(alpha = 0.9f), RoundedCornerShape(16.dp))
                            .border(1.dp, Color(0xFF00FFCC), RoundedCornerShape(16.dp))
                            .padding(10.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "QUANTUM IN-GAME HUD",
                                    color = Color(0xFF00FFCC),
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Black,
                                    fontFamily = FontFamily.Monospace
                                )
                                Button(
                                    onClick = { stopSelf() },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                                    modifier = Modifier.height(20.dp),
                                    contentPadding = PaddingValues(4.dp)
                                ) {
                                    Text(text = "X", color = Color.White, fontSize = 8.sp)
                                }
                            }
                            Divider(color = Color(0xFF102A45))
                            Text(text = "Physics Core: Active", color = Color.White, fontSize = 8.sp)
                            Text(text = "Quantum Flux: 9.99 THz", color = Color(0xFF00E5FF), fontSize = 8.sp)
                            Text(text = "Zero-Point: Stable", color = Color(0xFF00FFCC), fontSize = 8.sp)
                            Spacer(modifier = Modifier.weight(1f))
                            Button(
                                onClick = { stopSelf() },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00FFCC)),
                                modifier = Modifier.fillMaxWidth().height(28.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(text = "MINIMIZE HUD", color = Color.Black, fontSize = 8.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        floatingView = composeView

        val layoutFlag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            WindowManager.LayoutParams.TYPE_PHONE
        }

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            layoutFlag,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = 40
            y = 120
        }

        windowManager?.addView(floatingView, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (floatingView != null) {
            windowManager?.removeView(floatingView)
            floatingView = null
        }
    }
}
