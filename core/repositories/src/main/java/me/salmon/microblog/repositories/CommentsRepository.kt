package me.salmon.microblog.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import me.salmon.microblog.cache.comment.CommentCacheMapper
import me.salmon.microblog.cache.comment.CommentDao
import me.salmon.microblog.models.Comment
import me.salmon.microblog.network.comment.CommentNetworkMapper
import me.salmon.microblog.network.comment.CommentService
import me.salmon.microblog.utils.DataState
import java.lang.Exception

class CommentsRepository  constructor(
    private val commentService: CommentService,
    private val commentDao: CommentDao,
    private val networkMapper: CommentNetworkMapper,
    private val cacheMapper: CommentCacheMapper
) {

    suspend fun getComments(commentId: Int): Flow<DataState<List<Comment>>> = flow {
        emitLoadingState(this)
        getCommentsCache(this, commentId)
        getCommentsFromNetwork(this, commentId)
    }

    suspend fun emitLoadingState(flow: FlowCollector<DataState<List<Comment>>>) {
        flow.emit(DataState.Loading)
    }

    suspend fun getCommentsCache(flow: FlowCollector<DataState<List<Comment>>>, postId: Int) {
        val comments = cacheMapper.mapFromEntities(commentDao.fetchComments(postId))
        flow.emit(DataState.Success(comments))
    }

    suspend fun getCommentsFromNetwork(flow: FlowCollector<DataState<List<Comment>>>, postId: Int) {
        try {
            val comments = networkMapper.mapFromEntities(commentService.getComments(postId))
            flow.emit(DataState.Success(comments))
            prepareCommentsCache(comments)
        } catch (e: Exception) {
            flow.emit(DataState.Error(e))
        }
    }

    suspend fun prepareCommentsCache(comments: List<Comment>) {
        var entities = cacheMapper.mapToEntities(comments)
        commentDao.insertComments(entities)
    }
}