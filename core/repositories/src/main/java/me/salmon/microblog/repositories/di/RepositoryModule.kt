package me.salmon.microblog.repositories.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import me.salmon.microblog.cache.author.AuthorCacheMapper
import me.salmon.microblog.cache.author.AuthorDao
import me.salmon.microblog.network.author.AuthorNetworkMapper
import me.salmon.microblog.network.author.AuthorsService
import me.salmon.microblog.repositories.AuthorsRepository

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
}