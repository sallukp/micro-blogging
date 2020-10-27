package me.salmon.microblog.repositories.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import me.salmon.microblog.cache.author.AuthorCacheMapper
import me.salmon.microblog.cache.author.AuthorDao
import me.salmon.microblog.cache.post.PostCacheMapper
import me.salmon.microblog.cache.post.PostDao
import me.salmon.microblog.network.author.AuthorNetworkMapper
import me.salmon.microblog.network.author.AuthorsService
import me.salmon.microblog.network.post.PostNetworkMapper
import me.salmon.microblog.network.post.PostsService
import me.salmon.microblog.repositories.AuthorsRepository
import me.salmon.microblog.repositories.PostsRepository

@InstallIn(ActivityComponent::class)
@Module
object RepositoryModule {

    @ActivityScoped
    @Provides
    fun providesAuthorsRepository(authorsService: AuthorsService,
                                  authorDao: AuthorDao,
                                  networkMapper: AuthorNetworkMapper,
                                  cacheMapper: AuthorCacheMapper): AuthorsRepository {
        return AuthorsRepository(authorsService, authorDao, networkMapper, cacheMapper)
    }

    @ActivityScoped
    @Provides
    fun providesPostsRepository(postsService: PostsService,
                                  postDao: PostDao,
                                  networkMapper: PostNetworkMapper,
                                  cacheMapper: PostCacheMapper): PostsRepository {
        return PostsRepository(postsService, postDao, networkMapper, cacheMapper)
    }
}