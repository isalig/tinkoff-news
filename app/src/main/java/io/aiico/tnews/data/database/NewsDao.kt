package io.aiico.tnews.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.aiico.tnews.domain.News
import io.reactivex.Single

@Dao
interface NewsDao {

    @Insert
    fun insertAll(news: List<News>)

    @Update
    fun update(news: News)

    @Query("SELECT * FROM news")
    fun getAllNews(): Single<List<News>>

    @Query("SELECT * FROM news WHERE id IS :id")
    fun getNews(id: String): Single<List<News>>

    @Query("DELETE FROM news")
    fun clearTable()

    @Transaction
    fun rewriteNews(news: List<News>) {
        clearTable()
        insertAll(news)
    }
}