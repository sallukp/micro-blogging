package me.salmon.microblog.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import me.salmon.microblog.cache.author.AuthorCacheEntity
import me.salmon.microblog.cache.author.AuthorDao
import me.salmon.microblog.cache.comment.CommentCacheEntity
import me.salmon.microblog.cache.comment.CommentDao
import me.salmon.microblog.cache.post.PostCacheEntity
import me.salmon.microblog.cache.post.PostDao

@Database(entities = [AuthorCacheEntity::class, PostCacheEntity::class, CommentCacheEntity::class], version = 1)
abstract class BlogCache: RoomDatabase() {

    abstract fun authorDao(): AuthorDao

    abstract fun postDao(): PostDao

    abstract fun commentDao(): CommentDao

    companion object {
        const val database_name = "blog.db"
    }
}