package com.example.unsplashv3.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PhotoDBEntity::class],
    version = 1,
    exportSchema = false
 )
abstract class AppDataBase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao

    companion object {
        const val DATABASE_NAME: String = "unsplash_db_3"
    }
}