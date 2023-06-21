package com.devexperts.chatapp.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Query("SELECT * FROM users WHERE username LIKE :username LIMIT 1")
    fun findByName(username: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)
}