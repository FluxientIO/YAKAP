package com.example.yakap.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yakap.ui.components.MoodSelection
import com.example.yakap.ui.viewmodels.MoodViewModel

@Composable
fun MoodTrackerScreen(viewModel: MoodViewModel, onSaved: () -> Unit) {
    val uiState = viewModel.uiState.value

    LaunchedEffect(uiState.saveSuccess) {
        if (uiState.saveSuccess) {
            onSaved()
            // Reset success state in ViewModel if needed, but here we just navigate
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "How are you feeling?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))

        MoodSelection(
            selectedMood = uiState.selectedMood,
            onMoodSelected = { viewModel.updateMood(it) }
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = uiState.note,
            onValueChange = { viewModel.updateNote(it) },
            label = { Text("Add a note (optional)") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { 
                viewModel.saveMood()
                // The actual navigation/feedback will be handled by the parent using LaunchedEffect on saveSuccess
            },
            enabled = uiState.selectedMood != null && !uiState.isSaving,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (uiState.isSaving) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Text("Save Mood")
            }
        }
    }
}
