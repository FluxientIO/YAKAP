package com.example.yakap.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yakap.data.models.Appointment
import com.example.yakap.ui.viewmodels.AppointmentViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ProfessionalCalendarScreen(viewModel: AppointmentViewModel) {
    val appointments by viewModel.getProfessionalAppointments("p1").collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Your Schedule", fontSize = 24.sp, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        if (appointments.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text(text = "No appointments scheduled", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(appointments) { appointment ->
                    AppointmentItem(appointment = appointment)
                }
            }
        }
    }
}

@Composable
fun AppointmentItem(appointment: Appointment) {
    val sdf = SimpleDateFormat("MMM dd, yyyy - HH:mm", Locale.getDefault())
    val dateTime = sdf.format(Date(appointment.timestamp))

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Patient ID: ${appointment.patientId}", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = dateTime, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = appointment.status.name,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}
