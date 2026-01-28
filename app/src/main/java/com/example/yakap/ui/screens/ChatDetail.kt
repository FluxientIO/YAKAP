package com.example.yakap.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yakap.data.models.ChatMessage
import com.example.yakap.ui.viewmodels.ChatViewModel

@Composable
fun ChatDetailScreen(
    conversationId: String,
    currentUserId: String,
    viewModel: ChatViewModel
) {
    val messages by viewModel.getMessages(conversationId).collectAsState()
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            reverseLayout = false // Latest messages at bottom
        ) {
            items(messages) { message ->
                MessageBubble(message = message, isMe = message.senderId == currentUserId)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = { Text("Type a message...") },
                modifier = Modifier.weight(1f),
                maxLines = 3
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {
                    if (inputText.isNotBlank()) {
                        viewModel.sendMessage(conversationId, currentUserId, inputText)
                        inputText = ""
                    }
                },
                enabled = inputText.isNotBlank()
            ) {
                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send")
            }
        }
    }
}

@Composable
fun MessageBubble(message: ChatMessage, isMe: Boolean) {
    val alignment = if (isMe) Alignment.CenterEnd else Alignment.CenterStart
    val bgColor = if (isMe) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer
    val textColor = if (isMe) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondaryContainer
    val shape = if (isMe) {
        RoundedCornerShape(12.dp, 12.dp, 0.dp, 12.dp)
    } else {
        RoundedCornerShape(0.dp, 12.dp, 12.dp, 12.dp)
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = alignment
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clip(shape)
                .background(bgColor)
                .padding(12.dp)
                .widthIn(max = 280.dp)
        ) {
            Text(text = message.content, color = textColor, fontSize = 16.sp)
        }
    }
}
