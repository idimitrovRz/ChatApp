package com.devexperts.chatapp.domain.interactor.message

import com.devexperts.chatapp.common.Resource
import com.devexperts.chatapp.data.local.message.Message
import com.devexperts.chatapp.data.local.message.MessageDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MessageInteractor(
    private val messageDao: MessageDao
) {
    fun getMessagesByUserId(userId: String): Flow<Resource<List<Message>>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(messageDao.getMessagesByUserId(userId)))
        } catch (e: Exception) {
            emit(Resource.Error("Can't load messages for user $userId"))
        }
    }

    fun insert(message: Message) = messageDao.insert(message)
}