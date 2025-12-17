package com.pdm.instacompose.login.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Int):User?

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String):User?

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>
}
