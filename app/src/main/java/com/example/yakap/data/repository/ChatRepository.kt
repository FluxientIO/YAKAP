package com.example.yakap.data.repository

import com.example.yakap.data.local.dao.ChatDao
import com.example.yakap.data.models.ChatMessage
import com.example.yakap.data.models.Conversation
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ChatRepository {
    fun getConversations(): Flow<List<Conversation>>
    fun getMessages(conversationId: String): Flow<List<ChatMessage>>
    suspend fun sendMessage(conversationId: String, senderId: String, content: String)
    suspend fun createConversation(participantIds: List<String>): String
}

class LocalChatRepository(private val chatDao: ChatDao) : ChatRepository {
    override fun getConversations(): Flow<List<Conversation>> = 
        chatDao.getAllConversations()

    override fun getMessages(conversationId: String): Flow<List<ChatMessage>> = 
        chatDao.getMessagesForConversation(conversationId)

    override suspend fun sendMessage(conversationId: String, senderId: String, content: String) {
        val timestamp = System.currentTimeMillis()
        val message = ChatMessage(
            id = UUID.randomUUID().toString(),
            conversationId = conversationId,
            senderId = senderId,
            content = content,
            timestamp = timestamp
        )
        chatDao.insertMessage(message)

        // Update conversation last message
        val conversation = chatDao.getConversationById(conversationId)
        conversation?.let {
            chatDao.updateConversation(it.copy(
                lastMessage = content,
                lastTimestamp = timestamp
            ))
        }
    }

    override suspend fun createConversation(participantIds: List<String>): String {
        val id = UUID.randomUUID().toString()
        val conversation = Conversation(
            id = id,
            participantIds = participantIds.joinToString(","),
            lastMessage = "",
            lastTimestamp = System.currentTimeMillis()
        )
        chatDao.insertConversation(conversation)
        return id
    }
}
