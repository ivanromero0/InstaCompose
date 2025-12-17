package com.pdm.instacompose.login.data

import kotlinx.coroutines.flow.Flow

class OfflineUsersRepository(private val userDao: UserDao) : UsersRepository  {
    override suspend fun getAllUsersStream(): Flow<List<User>> = userDao.getAllUsers()
    override suspend fun getUserByIdStream(id: Int): User? = userDao.getUserById(id)
    override suspend fun getUserByEmailStream(email: String): User? = userDao.getUserByEmail(email)
    override suspend fun insertUser(user: User) = userDao.insert(user)
    override suspend fun deleteUser(user: User) = userDao.delete(user)
    override suspend fun updateUser(user: User) = userDao.update(user)

}