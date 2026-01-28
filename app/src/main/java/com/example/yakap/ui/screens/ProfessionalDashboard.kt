package com.example.yakap.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yakap.ui.viewmodels.AppointmentViewModel
import com.example.yakap.ui.viewmodels.ProfessionalViewModel

@Composable
fun ProfessionalDashboardScreen(
    viewModel: ProfessionalViewModel,
    appointmentViewModel: AppointmentViewModel? = null
) {
    // For now, using a fixed professionalId for development
    val patients by viewModel.getPatients("p1").collectAsState()
    val appointments by appointmentViewModel?.getProfessionalAppointments("p1")?.collectAsState(initial = emptyList()) ?: remember { mutableStateOf(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Professional Dashboard",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DashboardStatCard(
                title = "Patients",
                value = patients.size.toString(),
                icon = Icons.Default.Person,
                modifier = Modifier.weight(1f)
            )
            DashboardStatCard(
                title = "Today's Apps",
                value = appointments.size.toString(), // Simplified count for now
                icon = Icons.Default.DateRange,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Announcements",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Text(
                text = "Welcome to the YAKAP Professional portal. You can now manage your patients and take session notes securely.",
                modifier = Modifier.padding(16.dp),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun DashboardStatCard(title: String, value: String, icon: ImageVector, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = title, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
