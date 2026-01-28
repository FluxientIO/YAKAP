package com.example.yakap.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfessionalDirectoryScreen(
    onProfessionalClick: (String) -> Unit,
    onChatClick: (String) -> Unit
) {
    // Mock data for professionals
    val professionals = listOf(
        ProfessionalInfo("p1", "Dr. Jane Smith", "Psychiatrist", 4.8),
        ProfessionalInfo("p2", "Dr. John Doe", "Clinical Psychologist", 4.9),
        ProfessionalInfo("p3", "Sarah Williams", "Psychometrician", 4.7)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Find a Professional", fontSize = 24.sp, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(professionals) { pro ->
                ProfessionalCard(
                    info = pro, 
                    onClick = { onProfessionalClick(pro.id) },
                    onChatClick = { onChatClick(pro.id) }
                )
            }
        }
    }
}

data class ProfessionalInfo(val id: String, val name: String, val expertise: String, val rating: Double)

@Composable
fun ProfessionalCard(info: ProfessionalInfo, onClick: () -> Unit, onChatClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = info.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = info.expertise, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Rating: ⭐ ${info.rating}", fontSize = 12.sp)
            }
            IconButton(onClick = onChatClick) {
                Icon(Icons.Default.Email, contentDescription = "Chat", tint = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
