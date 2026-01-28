package com.example.yakap.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yakap.data.models.AssessmentResult
import com.example.yakap.ui.viewmodels.AssessmentViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WellnessHubScreen(
    assessmentViewModel: AssessmentViewModel,
    onBreathingClick: () -> Unit,
    onAssessmentClick: () -> Unit
) {
    val history by assessmentViewModel.getHistory("u1").collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Wellness Tools", fontSize = 24.sp, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WellnessToolTile(
                title = "Breathing",
                description = "4-7-8 Exercise",
                icon = Icons.Default.Favorite,
                onClick = onBreathingClick,
                modifier = Modifier.weight(1f)
            )
            WellnessToolTile(
                title = "Assessment",
                description = "Take a Quiz",
                icon = Icons.Default.Info,
                onClick = onAssessmentClick,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Assessment History", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        if (history.isEmpty()) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(text = "No history yet", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(history) { result ->
                    AssessmentHistoryItem(result)
                }
            }
        }
    }
}

@Composable
fun WellnessToolTile(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(text = description, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSecondaryContainer)
        }
    }
}

@Composable
fun AssessmentHistoryItem(result: AssessmentResult) {
    val date = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(result.timestamp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = result.quizType.name, fontWeight = FontWeight.Bold)
                Text(text = date, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Text(text = result.interpretation, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Medium)
        }
    }
}
