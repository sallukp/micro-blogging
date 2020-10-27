package me.salmon.microblog.profile.ui.post

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.salmon.microblog.models.Author
import me.salmon.microblog.models.Post
import me.salmon.microblog.repositories.PostsRepository
import me.salmon.microblog.utils.DataState

class PostViewModel
@ViewModelInject
constructor(
    private val postsRepository: PostsRepository
) : ViewModel() {

    private var _dataState: MutableLiveData<DataState<List<Post>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Post>>>
        get () = _dataState

    fun setStateEvent(postStateEvent: PostStateEvent) {
        when(postStateEvent) {
            is PostStateEvent.GetPostEvent -> {
                if (_dataState.value !is DataState.Success) {
                    viewModelScope.launch {
                        fetchPosts(postStateEvent.data)
                    }
                }
            }
        }
    }

    private fun fetchPosts(authorId: Int) =
        viewModelScope.launch {
            postsRepository.getPosts(authorId).onEach { dataState ->
                _dataState.postValue(dataState)
            }.launchIn(viewModelScope)
        }

    sealed class PostStateEvent {
        data class GetPostEvent(val data: Int): PostStateEvent()
    }
}