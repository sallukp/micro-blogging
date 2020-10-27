package me.salmon.microblog.blog.ui.post

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import me.salmon.microblog.models.Author
import me.salmon.microblog.models.Post

class PostViewModel
@ViewModelInject
constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private var _post: MutableLiveData<Post> = savedStateHandle.getLiveData(state_key)

    val post: LiveData<Post>
        get () = _post

    fun setStateEvent(state: PostStateEvent) {
        when(state) {
            is PostStateEvent.GetPostEvent -> {
                _post.postValue(state.data)
            }
        }
    }

    sealed class PostStateEvent {
        data class GetPostEvent(val data: Post): PostStateEvent()
    }

    companion object {
        const val state_key = "state"
    }
}