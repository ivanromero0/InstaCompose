package com.pdm.instacompose.login.data

import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getAllUsersStream(): Flow<List<User>>
    suspend fun getUserByIdStream(id: Int): User?
    suspend fun getUserByEmailStream(email: String): User?
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
}