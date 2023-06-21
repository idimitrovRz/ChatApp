package com.devexperts.chatapp.domain.interactor.user

import com.devexperts.chatapp.common.Resource
import com.devexperts.chatapp.data.local.user.User
import com.devexperts.chatapp.data.local.user.UserDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FriendsInteractor(
    private val userDao: UserDao,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun getAll(): Flow<Resource<List<User>>> = flow {
        try {
            emit(Resource.Loading())
            val friends = userDao.getAll()
            if (friends.isEmpty()) {
                insertSomeDataInDb()
            }
            emit(Resource.Success(friends))
        } catch (e: Exception) {
            emit(Resource.Error("Can't load Friends"))
        }
    }.flowOn(defaultDispatcher)

    fun findByName(username: String): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(userDao.findByUsername(username)))
        } catch (e: Exception) {
            emit(Resource.Error("User $username does not exist."))
        }
    }.flowOn(defaultDispatcher)

    private fun insert(user: User) {
        userDao.insert(user)
    }

    private fun insertSomeDataInDb() {
        insert(
            user = User(
                id = 1,
                username = "Ivan Dimitrov",
                profileImageUrl = ""
            )
        )
        insert(
            user = User(
                id = 2,
                username = "John Smith",
                profileImageUrl = ""
            )
        )
        insert(
            user = User(
                id = 3,
                username = "Lorem Ipsum",
                profileImageUrl = ""
            )
        )
    }
}