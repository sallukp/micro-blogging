package me.salmon.microblog.blog.ui.comment

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.salmon.microblog.models.Comment
import me.salmon.microblog.repositories.CommentsRepository
import me.salmon.microblog.utils.DataState

class CommentViewModel
@ViewModelInject
constructor(val commentsRepository: CommentsRepository): ViewModel() {
    private var _dataState: MutableLiveData<DataState<List<Comment>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Comment>>>
        get () = _dataState

    fun setStateEvent(commentStateEvent: CommentStateEvent) {
        when(commentStateEvent) {
            is CommentStateEvent.GetCommentsEvent -> {
                if (_dataState.value !is DataState.Success) {
                    viewModelScope.launch {
                        fetchAuthors(commentStateEvent.data)
                    }
                }
            }
        }
    }

    private fun fetchAuthors(postId: Int) =
        viewModelScope.launch {
            commentsRepository.getComments(postId).onEach { dataState ->
                _dataState.postValue(dataState)
            }.launchIn(viewModelScope)
        }

    sealed class CommentStateEvent {
        data class GetCommentsEvent(val data: Int): CommentStateEvent()
    }
}