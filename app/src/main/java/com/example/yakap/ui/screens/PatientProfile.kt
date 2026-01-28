package com.example.yakap.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yakap.data.models.ConsultationNote
import com.example.yakap.data.models.Patient
import com.example.yakap.ui.viewmodels.ProfessionalViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PatientProfileScreen(
    patientId: String,
    viewModel: ProfessionalViewModel,
    onChatClick: (String) -> Unit
) {
    var patient by remember { mutableStateOf<Patient?>(null) }
    val moodHistory by viewModel.getPatientMoodHistory(patientId).collectAsState()
    val notes by viewModel.getNotesForPatient(patientId).collectAsState()
    var showNoteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(patientId) {
        patient = viewModel.getPatient(patientId)
    }

    Scaffold(
        floatingActionButton = {
            Column {
                FloatingActionButton(onClick = { onChatClick(patientId) }) {
                    Icon(Icons.Default.Email, contentDescription = "Chat")
                }
                Spacer(modifier = Modifier.height(8.dp))
                FloatingActionButton(onClick = { showNoteDialog = true }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Note")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            patient?.let { p ->
                Text(text = p.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = p.email, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(24.dp))

                TabRow(selectedTabIndex = 0) { // Placeholder for tabs
                    Tab(selected = true, onClick = {}, text = { Text("Overview") })
                }
                
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Recent Moods", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Box(modifier = Modifier.height(200.dp)) {
                    MoodHistoryList(history = moodHistory)
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "Consultation Notes", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(notes) { note ->
                        NoteItem(note = note)
                    }
                }
            } ?: run {
                CircularProgressIndicator()
            }
        }
    }

    if (showNoteDialog) {
        AddNoteDialog(
            onDismiss = { showNoteDialog = false },
            onSave = { content ->
                viewModel.saveNote(patientId, "p1", content)
                showNoteDialog = false
            }
        )
    }
}

@Composable
fun NoteItem(note: ConsultationNote) {
    val date = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(note.timestamp))
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = date, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = note.content, fontSize = 14.sp)
        }
    }
}

@Composable
fun AddNoteDialog(onDismiss: () -> Unit, onSave: (String) -> Unit) {
    var content by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("New Session Note") },
        text = {
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Note content") },
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )
        },
        confirmButton = {
            Button(onClick = { onSave(content) }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
