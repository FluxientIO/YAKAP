package com.example.yakap.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.yakap.data.models.ChatMessage
import com.example.yakap.data.models.Conversation
import com.example.yakap.data.repository.ChatRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChatViewModel(private val repository: ChatRepository) : ViewModel() {

    val conversations: StateFlow<List<Conversation>> = repository.getConversations()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun getMessages(conversationId: String): StateFlow<List<ChatMessage>> = 
        repository.getMessages(conversationId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun sendMessage(conversationId: String, senderId: String, content: String) {
        viewModelScope.launch {
            repository.sendMessage(conversationId, senderId, content)
        }
    }

    fun startNewConversation(participantIds: List<String>, onCreated: (String) -> Unit) {
        viewModelScope.launch {
            val id = repository.createConversation(participantIds)
            onCreated(id)
        }
    }

    class Factory(private val repository: ChatRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ChatViewModel(repository) as T
        }
    }
}
