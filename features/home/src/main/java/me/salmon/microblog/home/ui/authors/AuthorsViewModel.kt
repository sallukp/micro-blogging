package me.salmon.microblog.home.ui.authors

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.salmon.microblog.models.Author
import me.salmon.microblog.repositories.AuthorsRepository
import me.salmon.microblog.utils.DataState

class AuthorsViewModel
@ViewModelInject
constructor(
    private val authorsRepository: AuthorsRepository
) : ViewModel() {

    private var _dataState: MutableLiveData<DataState<List<Author>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Author>>>
        get () = _dataState

    fun setStateEvent(authorStateEvent: AuthorStateEvent) {
        when(authorStateEvent) {
            is AuthorStateEvent.GetAuthorEvent -> {
                if (_dataState.value !is DataState.Success) {
                    viewModelScope.launch {
                        fetchAuthors()
                    }
                }
            }
        }
    }

    private fun fetchAuthors() =
        viewModelScope.launch {
            authorsRepository.getAuthors().onEach { dataState ->
                _dataState.postValue(dataState)
            }.launchIn(viewModelScope)
        }

    sealed class AuthorStateEvent {
        object GetAuthorEvent: AuthorStateEvent()
    }
}