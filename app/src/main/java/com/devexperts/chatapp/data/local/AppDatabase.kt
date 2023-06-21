package com.devexperts.chatapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devexperts.chatapp.data.local.message.Message
import com.devexperts.chatapp.data.local.message.MessageDao
import com.devexperts.chatapp.data.local.user.User
import com.devexperts.chatapp.data.local.user.UserDao

@Database(entities = [User::class, Message::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun messageDao(): MessageDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "chat_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}