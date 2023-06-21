package com.devexperts.chatapp.data.local.message

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MessageDao {

    @Query("SELECT * FROM messages WHERE id LIKE :userId")
    fun getMessagesByUserId(userId: String): List<Message>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(message: Message)
}