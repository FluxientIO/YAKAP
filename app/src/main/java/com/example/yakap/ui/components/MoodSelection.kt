package com.example.yakap.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yakap.data.models.MoodType

@Composable
fun MoodSelection(
    selectedMood: MoodType?,
    onMoodSelected: (MoodType) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val moodOptions = listOf(
            MoodOption("🤩", "Great", MoodType.GREAT, Color(0xFF4CAF50)),
            MoodOption("😊", "Good", MoodType.GOOD, Color(0xFF8BC34A)),
            MoodOption("😐", "Neutral", MoodType.NEUTRAL, Color(0xFFFFEB3B)),
            MoodOption("😔", "Low", MoodType.LOW, Color(0xFFFF9800)),
            MoodOption("😢", "Bad", MoodType.BAD, Color(0xFFF44336))
        )

        moodOptions.forEach { option ->
            MoodItem(
                option = option,
                isSelected = selectedMood == option.type,
                onClick = { onMoodSelected(option.type) }
            )
        }
    }
}

data class MoodOption(val emoji: String, val label: String, val type: MoodType, val color: Color)

@Composable
fun MoodItem(
    option: MoodOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(4.dp)
    ) {
        IconButton(
            onClick = onClick,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = if (isSelected) option.color.copy(alpha = 0.2f) else Color.Transparent
            ),
            modifier = Modifier.size(56.dp)
        ) {
            Text(text = option.emoji, fontSize = 32.sp)
        }
        Text(
            text = option.label,
            fontSize = 12.sp,
            color = if (isSelected) option.color else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
