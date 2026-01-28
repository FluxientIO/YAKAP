package com.example.yakap.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
fun AvailabilityManagementScreen(viewModel: AppointmentViewModel) {
    val slots by viewModel.getAllSlots("p1").collectAsState() // Fixed professionalId for now

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Manage Availability", fontSize = 24.sp, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { 
                // In a real app, show time picker. Here we'll add a dummy slot for demonstration.
                val start = System.currentTimeMillis() + (3600000 * 24) // Tomorrow
                viewModel.createSlot("p1", start, start + 3600000)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Available Slot (Tomorrow)")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Your Slots", fontSize = 18.sp, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(slots) { slot ->
                SlotItem(slot = slot, onDelete = { viewModel.deleteSlot(slot) })
            }
        }
    }
}

@Composable
fun SlotItem(slot: AppointmentSlot, onDelete: () -> Unit) {
    val sdf = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault())
    val timeRange = "${sdf.format(Date(slot.startTime))} - ${sdf.format(Date(slot.endTime))}"

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (slot.isBooked) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = timeRange, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                if (slot.isBooked) {
                    Text(text = "BOOKED", fontSize = 12.sp, color = MaterialTheme.colorScheme.error)
                } else {
                    Text(text = "Available", fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                }
            }
            if (!slot.isBooked) {
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}
