package me.salmon.microblog.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import me.salmon.microblog.cache.post.PostCacheMapper
import me.salmon.microblog.cache.post.PostDao
import me.salmon.microblog.models.Post
import me.salmon.microblog.network.post.PostNetworkMapper
import me.salmon.microblog.network.post.PostsService
import me.salmon.microblog.utils.DataState
import java.lang.Exception

class PostsRepository constructor(
    private val postsService: PostsService,
    private val postDao: PostDao,
    private val networkMapper: PostNetworkMapper,
    private val cacheMapper: PostCacheMapper
) {

    suspend fun getPosts(authorId: Int): Flow<DataState<List<Post>>> = flow {
        emitLoadingState(this)
        getPostsCache(this, authorId)
        getPostsFromNetwork(this, authorId)
    }

    suspend fun emitLoadingState(flow: FlowCollector<DataState<List<Post>>>) {
        flow.emit(DataState.Loading)
    }

    suspend fun getPostsCache(flow: FlowCollector<DataState<List<Post>>>, authorId: Int) {
        val posts = cacheMapper.mapFromEntities(postDao.fetchPosts(authorId))
        flow.emit(DataState.Success(posts))
    }

    suspend fun getPostsFromNetwork(flow: FlowCollector<DataState<List<Post>>>, authorId: Int) {
        try {
            val posts = networkMapper.mapFromEntities(postsService.getPosts(authorId))
            flow.emit(DataState.Success(posts))
            preparePostsCache(posts)
        } catch (e: Exception) {
            flow.emit(DataState.Error(e))
        }
    }

    suspend fun preparePostsCache(posts: List<Post>) {
        var entities = cacheMapper.mapToEntities(posts)
        postDao.insertPosts(entities)
    }

}