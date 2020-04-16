package io.aiico.tnews.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.aiico.tnews.domain.News

@Database(
    entities = [News::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDb : RoomDatabase() {

    abstract val newsDao: NewsDao

    companion object {

        fun create(context: Context): NewsDb =
            Room
                .databaseBuilder(context, NewsDb::class.java, "newsdatabese")
                .build()
    }
}