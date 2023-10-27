package com.example.challenge4.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.challenge4.core.data.source.local.entity.BookEntity
import com.example.challenge4.core.data.source.local.entity.UserEntity
import com.example.challenge4.core.utils.FileConverter

@Database(entities = [BookEntity::class, UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(FileConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "BookPedia.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}