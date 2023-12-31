package com.androidhacks.merakiliteai.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androidhacks.merakiliteai.converter.CourseListConverter

@Database(
    entities = [PathwayEntity::class],
    version = 1
)
@TypeConverters(CourseListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pathwayDao(): PathwayDao

    companion object {
        const val DATABASE_NAME = "app_database"
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return instance!!
        }
    }
}