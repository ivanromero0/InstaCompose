package com.pdm.instacompose.login.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class InstagramDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: InstagramDatabase? = null


        //Crear base de datos
        //esto se suele copiar y pegar, no hace falta memorizarlo
        fun getDataBase(context:Context): InstagramDatabase{

            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context,
                    InstagramDatabase::class.java,
                    /*name=*/"nstagram_database")
            }.fallbackToDestructiveMigration(true)
                .build()
                .also { Instance = it }
        }
    }
}