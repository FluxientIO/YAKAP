package com.example.yakap.data.repository

import com.example.yakap.data.local.dao.ChatDao
import com.example.yakap.data.models.ChatMessage
import com.example.yakap.data.models.Conversation
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ChatRepositoryTest {

    private val chatDao = mockk<ChatDao>()
    private val repository = LocalChatRepository(chatDao)

    @Test
    fun sendMessage_insertsMessageAndUpdatesConversation() = runBlocking {
        val conversation = Conversation("c1", "u1,p1", "Old", 100L)
        coEvery { chatDao.getConversationById("c1") } returns conversation
        coEvery { chatDao.insertMessage(any()) } returns Unit
        coEvery { chatDao.updateConversation(any()) } returns Unit

        repository.sendMessage("c1", "u1", "Hello")

        coVerify { chatDao.insertMessage(match { it.content == "Hello" && it.conversationId == "c1" }) }
        coVerify { chatDao.updateConversation(match { it.id == "c1" && it.lastMessage == "Hello" }) }
    }

    @Test
    fun getConversations_returnsFlow() = runBlocking {
        val conversations = listOf(Conversation("c1", "u1,p1", "Hi", 123L))
        every { chatDao.getAllConversations() } returns flowOf(conversations)

        val result = repository.getConversations().first()

        assertEquals(1, result.size)
        assertEquals("Hi", result[0].lastMessage)
    }
}
