package com.example.yakap.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yakap.data.models.AppointmentSlot
import com.example.yakap.ui.viewmodels.AppointmentViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun BookingScreen(
    professionalId: String,
    viewModel: AppointmentViewModel,
    onBookingConfirmed: () -> Unit
) {
    val availableSlots by viewModel.getAvailableSlots(professionalId).collectAsState()
    var selectedSlot by remember { mutableStateOf<AppointmentSlot?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Book an Appointment", fontSize = 24.sp, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Select a time slot with the professional", color = MaterialTheme.colorScheme.onSurfaceVariant)
        
        Spacer(modifier = Modifier.height(24.dp))

        if (availableSlots.isEmpty()) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(text = "No available slots for this professional.")
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(availableSlots) { slot ->
                    SelectableSlotItem(
                        slot = slot,
                        isSelected = selectedSlot?.id == slot.id,
                        onClick = { selectedSlot = slot }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                selectedSlot?.let { slot ->
                    viewModel.bookAppointment("u1", professionalId, slot.id) // Fixed patientId for now
                    onBookingConfirmed()
                }
            },
            enabled = selectedSlot != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Confirm Booking")
        }
    }
}

@Composable
fun SelectableSlotItem(slot: AppointmentSlot, isSelected: Boolean, onClick: () -> Unit) {
    val sdf = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault())
    val timeRange = "${sdf.format(Date(slot.startTime))} - ${sdf.format(Date(slot.endTime))}"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
        ),
        border = if (isSelected) androidx.compose.foundation.BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = timeRange, fontWeight = if (isSelected) androidx.compose.ui.text.font.FontWeight.Bold else null)
        }
    }
}
