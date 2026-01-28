package com.example.yakap.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yakap.data.models.Appointment
import com.example.yakap.data.models.MoodEntry
import com.example.yakap.data.models.MoodType
import com.example.yakap.ui.components.EmptyState
import com.example.yakap.ui.components.MoodTrendChart
import com.example.yakap.ui.viewmodels.AppointmentViewModel
import com.example.yakap.ui.viewmodels.MoodViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DashboardScreen(
    viewModel: MoodViewModel,
    appointmentViewModel: AppointmentViewModel? = null
) {
    val moodHistory by viewModel.moodHistory.collectAsState()
    val appointments by appointmentViewModel?.getPatientAppointments("u1")?.collectAsState(initial = emptyList()) ?: remember { mutableStateOf(emptyList()) }
    
    DashboardContent(
        moodHistory = moodHistory,
        upcomingAppointments = appointments
    )
}

@Composable
fun DashboardContent(
    moodHistory: List<MoodEntry>,
    upcomingAppointments: List<Appointment>
) {
    val latestMood = moodHistory.firstOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Welcome Back",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))

        LatestMoodCard(latestMood)

        Spacer(modifier = Modifier.height(24.dp))

        if (upcomingAppointments.isNotEmpty()) {
            Text(
                text = "Upcoming Appointments",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            UpcomingAppointmentsList(upcomingAppointments)
            Spacer(modifier = Modifier.height(24.dp))
        }

        if (moodHistory.isNotEmpty()) {
            MoodTrendChart(moodHistory = moodHistory)
            Spacer(modifier = Modifier.height(24.dp))
        }

        Text(
            text = "Recent History",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        MoodHistoryList(moodHistory)
    }
}

@Composable
fun UpcomingAppointmentsList(appointments: List<Appointment>) {
    // Show only next 2 appointments
    val displayList = appointments.take(2)
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        displayList.forEach { appointment ->
            AppointmentSummaryCard(appointment)
        }
    }
}

@Composable
fun AppointmentSummaryCard(appointment: Appointment) {
    val sdf = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault())
    val dateTime = sdf.format(Date(appointment.timestamp)) // Ideally use slot start time

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Consultation", fontWeight = FontWeight.Bold)
                Text(text = dateTime, fontSize = 14.sp)
            }
            Text(text = appointment.status.name, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun LatestMoodCard(mood: MoodEntry?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your Latest Mood",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (mood != null) {
                Text(text = getEmojiForMood(mood.moodType), fontSize = 64.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = mood.moodType.name.lowercase().replaceFirstChar { it.uppercase() },
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                if (mood.note.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = mood.note,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
                    )
                }
            } else {
                Text(text = "No entries yet", fontSize = 18.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun MoodHistoryList(history: List<MoodEntry>) {
    if (history.isEmpty()) {
        EmptyState(message = "No mood entries yet. How are you feeling today?")
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(history) { entry ->
                MoodHistoryItem(entry)
            }
        }
    }
}

@Composable
fun MoodHistoryItem(entry: MoodEntry) {
    val date = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault()).format(Date(entry.timestamp))
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = getEmojiForMood(entry.moodType), fontSize = 32.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = entry.moodType.name.lowercase().replaceFirstChar { it.uppercase() },
                fontWeight = FontWeight.Bold
            )
            Text(text = date, fontSize = 12.sp, color = Color.Gray)
            if (entry.note.isNotEmpty()) {
                Text(text = entry.note, fontSize = 14.sp, maxLines = 1)
            }
        }
    }
}

fun getEmojiForMood(type: MoodType): String {
    return when (type) {
        MoodType.GREAT -> "🤩"
        MoodType.GOOD -> "😊"
        MoodType.NEUTRAL -> "😐"
        MoodType.LOW -> "😔"
        MoodType.BAD -> "😢"
    }
}
