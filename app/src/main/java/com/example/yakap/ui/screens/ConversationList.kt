package com.example.yakap.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yakap.data.models.Conversation
import com.example.yakap.ui.components.EmptyState
import com.example.yakap.ui.viewmodels.ChatViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ConversationListScreen(
    viewModel: ChatViewModel,
    onConversationClick: (String) -> Unit
) {
    val conversations by viewModel.conversations.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Messages", fontSize = 24.sp, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        if (conversations.isEmpty()) {
            EmptyState(message = "No messages yet. Start a conversation with a professional!")
        } else {
            LazyColumn {
                items(conversations) { conversation ->
                    ConversationItem(
                        conversation = conversation,
                        onClick = { onConversationClick(conversation.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun ConversationItem(conversation: Conversation, onClick: () -> Unit) {
    val date = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(conversation.lastTimestamp))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Conversation ${conversation.id.take(4)}", fontWeight = FontWeight.Bold) // Simplified name
                Text(
                    text = if (conversation.lastMessage.isEmpty()) "Start chatting..." else conversation.lastMessage,
                    fontSize = 14.sp,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(text = date, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
