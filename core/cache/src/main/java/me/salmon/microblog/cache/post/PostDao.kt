package me.salmon.microblog.cache.post

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(entity: PostCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(entities: List<PostCacheEntity>): List<Long>

    @Query("SELECT * FROM post WHERE aId == :authorId ORDER BY id ASC")
    suspend fun fetchPosts(authorId: Int): List<PostCacheEntity>
}