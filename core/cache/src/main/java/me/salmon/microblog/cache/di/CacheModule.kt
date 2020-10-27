package me.salmon.microblog.cache.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import me.salmon.microblog.cache.BlogCache
import me.salmon.microblog.cache.author.AuthorDao
import me.salmon.microblog.cache.comment.CommentDao
import me.salmon.microblog.cache.post.PostDao
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object CacheModule {

    @Singleton
    @Provides
    fun provideBlogCache(@ApplicationContext context: Context): BlogCache =
        Room.databaseBuilder(context, BlogCache::class.java, BlogCache.database_name)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideAuthorDao(database: BlogCache): AuthorDao = database.authorDao()

    @Singleton
    @Provides
    fun providePostDao(database: BlogCache): PostDao = database.postDao()

    @Singleton
    @Provides
    fun provideCommentsDao(database: BlogCache): CommentDao = database.commentDao()

}