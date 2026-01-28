package com.example.yakap.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversations")
data class Conversation(
    @PrimaryKey val id: String,
    val participantIds: String, // Comma-separated IDs for simplicity in Room
    val lastMessage: String,
    val lastTimestamp: Long
)

@Entity(tableName = "chat_messages")
data class ChatMessage(
    @PrimaryKey val id: String,
    val conversationId: String,
    val senderId: String,
    val content: String,
    val timestamp: Long
)
