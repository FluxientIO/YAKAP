package com.example.yakap.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun BreathingExerciseScreen() {
    var isRunning by remember { mutableStateOf(false) }
    var currentPhase by remember { mutableStateOf("Ready") }
    var progress by remember { mutableStateOf(0f) }

    val bgColor by animateColorAsState(
        targetValue = when (currentPhase) {
            "Inhale" -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f)
            "Hold" -> MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f)
            "Exhale" -> MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.2f)
            else -> MaterialTheme.colorScheme.background
        },
        animationSpec = tween(2000),
        label = "bgColor"
    )

    val circleColor by animateColorAsState(
        targetValue = when (currentPhase) {
            "Inhale" -> MaterialTheme.colorScheme.primary
            "Hold" -> MaterialTheme.colorScheme.secondary
            "Exhale" -> MaterialTheme.colorScheme.tertiary
            else -> MaterialTheme.colorScheme.surfaceVariant
        },
        animationSpec = tween(1000),
        label = "circleColor"
    )

    // Manual control over phases for 4-7-8 timing
    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (isRunning) {
                // Inhale (4s)
                currentPhase = "Inhale"
                val inhaleStart = System.currentTimeMillis()
                while (System.currentTimeMillis() - inhaleStart < 4000 && isRunning) {
                    progress = (System.currentTimeMillis() - inhaleStart) / 4000f
                    delay(16)
                }
                
                // Hold (7s)
                if (!isRunning) break
                currentPhase = "Hold"
                val holdStart = System.currentTimeMillis()
                while (System.currentTimeMillis() - holdStart < 7000 && isRunning) {
                    progress = 1f
                    delay(16)
                }

                // Exhale (8s)
                if (!isRunning) break
                currentPhase = "Exhale"
                val exhaleStart = System.currentTimeMillis()
                while (System.currentTimeMillis() - exhaleStart < 8000 && isRunning) {
                    progress = 1f - (System.currentTimeMillis() - exhaleStart) / 8000f
                    delay(16)
                }
            }
        } else {
            currentPhase = "Ready"
            progress = 0.6f
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Breathe",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(48.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(300.dp)
            ) {
                // Animated circle with smoother easing
                val animatedScale = if (isRunning) {
                    val easedProgress = if (currentPhase == "Inhale") {
                        FastOutSlowInEasing.transform(progress)
                    } else if (currentPhase == "Exhale") {
                        1f - FastOutSlowInEasing.transform(1f - progress)
                    } else progress

                    if (currentPhase == "Inhale") 0.6f + (easedProgress * 0.4f)
                    else if (currentPhase == "Hold") 1.0f
                    else 1.0f - (easedProgress * 0.4f)
                } else 0.6f

                Box(
                    modifier = Modifier
                        .size((300 * animatedScale).dp)
                        .clip(CircleShape)
                        .background(circleColor.copy(alpha = 0.4f))
                )

                Text(
                    text = currentPhase,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(64.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { isRunning = !isRunning },
                    modifier = Modifier.width(120.dp)
                ) {
                    Text(if (isRunning) "Pause" else "Start")
                }

                if (!isRunning && currentPhase != "Ready") {
                    OutlinedButton(
                        onClick = { 
                            isRunning = false
                            currentPhase = "Ready"
                        },
                        modifier = Modifier.width(120.dp)
                    ) {
                        Text("Reset")
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "4-7-8 Pattern: Inhale 4s, Hold 7s, Exhale 8s",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}