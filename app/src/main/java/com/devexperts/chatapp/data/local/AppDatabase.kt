package com.devexperts.chatapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devexperts.chatapp.data.local.message.Message
import com.devexperts.chatapp.data.local.message.MessageDao
import com.devexperts.chatapp.data.local.user.User
import com.devexperts.chatapp.data.local.user.UserDao

@Database(entities = [User::class, Message::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun messageDao(): MessageDao
}