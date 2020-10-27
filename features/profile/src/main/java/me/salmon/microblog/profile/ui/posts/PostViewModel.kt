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
    private val postsRepository: PostsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _dataState: MutableLiveData<DataState<List<Post>>> = MutableLiveData()

    private var _savedState: MutableLiveData<Boolean> = savedStateHandle.getLiveData(state_key)

    val dataState: LiveData<DataState<List<Post>>>
        get () = _dataState

    fun setStateEvent(postStateEvent: PostStateEvent) {
        viewModelScope.launch {
            when (postStateEvent) {
                is PostStateEvent.GetPostEvent -> {
                    fetchPosts(postStateEvent.data)
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

    companion object {
        const val state_key = "state"
    }
}