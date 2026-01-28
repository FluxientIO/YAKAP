package com.example.yakap.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yakap.data.models.Patient
import com.example.yakap.ui.components.EmptyState
import com.example.yakap.ui.viewmodels.ProfessionalViewModel

@Composable
fun PatientListScreen(
    viewModel: ProfessionalViewModel,
    onPatientClick: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val patients by if (searchQuery.isEmpty()) {
        viewModel.getPatients("p1").collectAsState()
    } else {
        viewModel.searchPatients(searchQuery).collectAsState()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search Patients") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        if (patients.isEmpty()) {
            EmptyState(message = "No patients assigned yet. They will appear here once connected.")
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(patients) { patient ->
                    PatientListItem(patient = patient, onClick = { onPatientClick(patient.id) })
                }
            }
        }
    }
}

@Composable
fun PatientListItem(patient: Patient, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = patient.name, fontSize = 18.sp)
                Text(text = patient.email, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}