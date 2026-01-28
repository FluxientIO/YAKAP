package com.example.yakap.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yakap.ui.viewmodels.AdminViewModel

@Composable
fun AdminDashboardScreen(viewModel: AdminViewModel) {
    val totalUsers by viewModel.totalUserCount.collectAsState()
    val activePros by viewModel.activeProfessionalCount.collectAsState()
    val pendingVerifications by viewModel.pendingVerifications.collectAsState()
    val totalMoods by viewModel.totalMoodEntries.collectAsState()
    val totalApps by viewModel.totalAppointments.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Platform Overview", fontSize = 24.sp, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(24.dp))

        // Main Stats Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AdminStatCard(
                title = "Total Users",
                value = totalUsers.toString(),
                icon = Icons.Default.Person,
                modifier = Modifier.weight(1f)
            )
            AdminStatCard(
                title = "Active Pros",
                value = activePros.toString(),
                icon = Icons.Default.CheckCircle,
                modifier = Modifier.weight(1f),
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Engagement Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AdminStatCard(
                title = "Mood Entries",
                value = totalMoods.toString(),
                icon = Icons.Default.Info,
                modifier = Modifier.weight(1f)
            )
            AdminStatCard(
                title = "Sessions",
                value = totalApps.toString(),
                icon = Icons.AutoMirrored.Filled.List,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Actionable Verification Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = if (pendingVerifications.isNotEmpty()) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (pendingVerifications.isNotEmpty()) Icons.Default.Warning else Icons.Default.Info,
                    contentDescription = null,
                    tint = if (pendingVerifications.isNotEmpty()) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = "${pendingVerifications.size} Pending Verifications", fontWeight = FontWeight.Bold)
                    Text(text = "Professionals awaiting approval", fontSize = 12.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "System Health", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        
        SystemHealthItem(label = "Database Connection", status = "HEALTHY", isHealthy = true)
        Spacer(modifier = Modifier.height(8.dp))
        SystemHealthItem(label = "Message Relay", status = "ONLINE", isHealthy = true)
    }
}

@Composable
fun SystemHealthItem(label: String, status: String, isHealthy: Boolean) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = label)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .padding(end = 4.dp)
                        .background(if (isHealthy) androidx.compose.ui.graphics.Color(0xFF4CAF50) else MaterialTheme.colorScheme.error, shape = androidx.compose.foundation.shape.CircleShape)
                )
                Text(
                    text = status, 
                    color = if (isHealthy) androidx.compose.ui.graphics.Color(0xFF4CAF50) else MaterialTheme.colorScheme.error, 
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun AdminStatCard(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    containerColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.surfaceVariant
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
            Text(text = title, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}