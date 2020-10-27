package me.salmon.microblog.profile.ui.profile

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import me.salmon.microblog.models.Author

class ProfileViewModel
@ViewModelInject
constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private var _author: MutableLiveData<Author> = savedStateHandle.getLiveData(status_key)

    val author: LiveData<Author>
        get () = _author

    fun setStateEvent(state: ProfileStateEvent) {
        when(state) {
            is ProfileStateEvent.GetProfileState -> {
                _author.postValue(state.data)
            }
        }
    }

    sealed class ProfileStateEvent {
        data class GetProfileState(val data: Author): ProfileStateEvent()
    }

    companion object {
        const val status_key = "status"
    }
}