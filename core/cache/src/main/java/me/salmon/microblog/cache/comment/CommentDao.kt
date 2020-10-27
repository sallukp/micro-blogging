package me.salmon.microblog.cache.comment

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.salmon.microblog.cache.post.PostCacheEntity

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(entity: CommentCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(entities: List<CommentCacheEntity>): List<Long>

    @Query("SELECT * FROM comment WHERE pId == :postId ORDER BY id ASC")
    suspend fun fetchComments(postId: Int): List<CommentCacheEntity>
}