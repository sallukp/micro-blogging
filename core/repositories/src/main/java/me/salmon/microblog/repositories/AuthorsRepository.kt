package me.salmon.microblog.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import me.salmon.microblog.cache.author.AuthorCacheMapper
import me.salmon.microblog.cache.author.AuthorDao
import me.salmon.microblog.models.Author
import me.salmon.microblog.network.author.AuthorNetworkMapper
import me.salmon.microblog.network.author.AuthorsService
import me.salmon.microblog.utils.DataState
import java.lang.Exception

class AuthorsRepository
constructor(
    private val authorService: AuthorsService,
    private val authorDao: AuthorDao,
    private val networkMapper: AuthorNetworkMapper,
    private val cacheMapper: AuthorCacheMapper) {

    suspend fun getAuthors(): Flow<DataState<List<Author>>> = flow {

    }

    suspend fun getAuthorsCache(flow: FlowCollector<DataState<List<Author>>>) {
        val authors = cacheMapper.mapFromEntities(authorDao.fetchAuthors())
        flow.emit(DataState.Success(authors))
    }

    suspend fun getAuthorsFromNetwork(flow: FlowCollector<DataState<List<Author>>>) {
        try {
            val authors = networkMapper.mapFromEntities(authorService.fetchAuthors())
            flow.emit(DataState.Success(authors))
            prepareAuthorsCache(authors)
        } catch (e: Exception) {
            flow.emit(DataState.Error(e))
        }
    }

    suspend fun prepareAuthorsCache(authors: List<Author>) {
        var entities = cacheMapper.mapToEntities(authors)
        authorDao.insertAuthors(entities)
    }
}