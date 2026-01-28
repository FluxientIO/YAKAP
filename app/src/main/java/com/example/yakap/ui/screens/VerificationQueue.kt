package com.example.yakap.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yakap.data.models.UserAccount
import com.example.yakap.ui.components.EmptyState
import com.example.yakap.ui.viewmodels.AdminViewModel

@Composable
fun VerificationQueueScreen(viewModel: AdminViewModel) {
    val pendingUsers by viewModel.pendingVerifications.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Pending Verifications", fontSize = 24.sp, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        if (pendingUsers.isEmpty()) {
            EmptyState(message = "All caught up! No pending professional verifications.")
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(pendingUsers) { user ->
                    VerificationItem(
                        user = user,
                        onApprove = { viewModel.verifyProfessional(user.id, true) },
                        onReject = { viewModel.verifyProfessional(user.id, false) }
                    )
                }
            }
        }
    }
}

@Composable
fun VerificationItem(user: UserAccount, onApprove: () -> Unit, onReject: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = user.name, fontSize = 18.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            Text(text = user.email, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(text = "License Number: ${user.licenseNumber ?: "N/A"}", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onReject, colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)) {
                    Text("Reject")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onApprove) {
                    Text("Approve")
                }
            }
        }
    }
}
