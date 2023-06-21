package com.devexperts.chatapp.data.local.message

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface MessageDao {

    @Query("SELECT * FROM messages WHERE id LIKE :userId")
    suspend fun getMessagedByUserId(userId: String): List<Message>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: Message)
}