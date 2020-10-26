package me.salmon.microblog.cache.author

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AuthorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuthor(entity: AuthorCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuthors(entities: List<AuthorCacheEntity>): List<Long>

    @Query("SELECT * FROM author ORDER BY id ASC")
    suspend fun fetchAuthors(): List<AuthorCacheEntity>
}