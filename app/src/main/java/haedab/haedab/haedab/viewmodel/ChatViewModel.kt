package haedab.haedab.haedab.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import haedab.haedab.haedab.database.RoomEntity
import haedab.haedab.haedab.repository.ChatRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val repository: ChatRepository) : ViewModel() {
    private val _messageList = MutableStateFlow<List<RoomEntity>>(emptyList())

    val allMessageList: Flow<List<RoomEntity>> = repository.allMessages

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    suspend fun loadingStart() {
        _isLoading.emit(false)
    }
    suspend fun loadingEnd() {
        _isLoading.emit(true)
    }


    suspend fun getResponse(query: String) {

        addMessage(RoomEntity(0,query, "user"))

            //emitTyping()

        viewModelScope.launch {
            val chatModel = repository.getResponse(query)
            chatModel.collectLatest {
                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) {
                        loadingEnd()
                        repository.deleteMessages()
                        addMessage(it)
                        loadingStart()
                    }
                }
            }

        }


    }

    suspend fun deleteAll() {
        repository.deleteAllMessages()
    }

    private suspend fun emitTyping() {
        viewModelScope.launch {
            //delay(1000)
            addMessage(RoomEntity(0, "", "bot"))
        }
    }

    /*suspend fun botWelcomeMessage() {
        viewModelScope.launch {
            delay(100)
            addMessage(RoomEntity(0, "Hello! How can i help you?", "bot"))
        }
    }*/

    private suspend fun addMessage(roomEntity: RoomEntity) {
        val messages = _messageList.value.toMutableList()
        messages.add(roomEntity)
        repository.insertMessage(roomEntity)
        _messageList.value = messages
    }
}