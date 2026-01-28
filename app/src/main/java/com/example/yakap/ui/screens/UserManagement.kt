package com.example.yakap.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yakap.data.models.UserAccount
import com.example.yakap.ui.models.UserRole
import com.example.yakap.ui.viewmodels.AdminViewModel

@Composable
fun UserManagementScreen(viewModel: AdminViewModel) {
    val users by viewModel.allUsers.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "All Users", fontSize = 24.sp, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(users) { user ->
                UserAccountItem(user = user)
            }
        }
    }
}

@Composable
fun UserAccountItem(user: UserAccount) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = user.name, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                Text(text = user.email, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                RoleChip(role = user.role)
                if (user.role == UserRole.PROFESSIONAL) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (user.isVerified) "✓ Verified" else "UNVERIFIED",
                        fontSize = 10.sp,
                        color = if (user.isVerified) androidx.compose.ui.graphics.Color(0xFF4CAF50) else MaterialTheme.colorScheme.error,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}

@Composable
fun RoleChip(role: UserRole) {
    val color = when (role) {
        UserRole.PATIENT -> MaterialTheme.colorScheme.primaryContainer
        UserRole.PROFESSIONAL -> MaterialTheme.colorScheme.secondaryContainer
        UserRole.ADMIN -> MaterialTheme.colorScheme.tertiaryContainer
    }
    Surface(
        color = color,
        shape = androidx.compose.foundation.shape.CircleShape
    ) {
        Text(
            text = role.name,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
