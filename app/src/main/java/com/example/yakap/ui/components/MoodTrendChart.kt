package com.example.yakap.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yakap.data.models.MoodEntry
import com.example.yakap.data.models.MoodType

@Composable
fun MoodTrendChart(moodHistory: List<MoodEntry>, modifier: Modifier = Modifier) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val labelColor = MaterialTheme.colorScheme.onSurfaceVariant

    Column(modifier = modifier) {
        Text(
            text = "Mood Trends (Last 7 Entries)",
            fontSize = 14.sp,
            color = labelColor,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            val width = size.width
            val height = size.height
            val padding = 20.dp.toPx()
            
            val chartWidth = width - (padding * 2)
            val chartHeight = height - (padding * 2)

            // Mood levels mapping to Y coordinate
            val moodLevels = listOf(MoodType.GREAT, MoodType.GOOD, MoodType.NEUTRAL, MoodType.LOW, MoodType.BAD)
            fun getY(moodType: MoodType): Float {
                val index = moodLevels.indexOf(moodType)
                return padding + (index.toFloat() / (moodLevels.size - 1)) * chartHeight
            }

            // Draw grid lines (horizontal)
            moodLevels.forEachIndexed { index, _ ->
                val y = padding + (index.toFloat() / (moodLevels.size - 1)) * chartHeight
                drawLine(
                    color = labelColor.copy(alpha = 0.1f),
                    start = Offset(padding, y),
                    end = Offset(width - padding, y),
                    strokeWidth = 1.dp.toPx()
                )
            }

            // Take last 7 entries
            val displayData = moodHistory.take(7).reversed()
            if (displayData.size > 1) {
                val stepX = chartWidth / (displayData.size - 1)
                val path = Path()
                
                displayData.forEachIndexed { index, entry ->
                    val x = padding + (index * stepX)
                    val y = getY(entry.moodType)
                    
                    if (index == 0) {
                        path.moveTo(x, y)
                    } else {
                        path.lineTo(x, y)
                    }
                    
                    // Draw dots
                    drawCircle(
                        color = primaryColor,
                        radius = 4.dp.toPx(),
                        center = Offset(x, y)
                    )
                }
                
                drawPath(
                    path = path,
                    color = primaryColor,
                    style = Stroke(width = 3.dp.toPx())
                )
            } else if (displayData.size == 1) {
                // Just one point
                drawCircle(
                    color = primaryColor,
                    radius = 4.dp.toPx(),
                    center = Offset(width / 2, getY(displayData[0].moodType))
                )
            }
        }
    }
}
