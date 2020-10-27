package me.salmon.microblog.home.ui.authors

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import me.salmon.microblog.models.Author
import me.salmon.microblog.repositories.AuthorsRepository
import me.salmon.microblog.utils.DataState
import org.junit.After
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Spy
import org.powermock.api.mockito.PowerMockito.mock
import org.powermock.modules.junit4.PowerMockRunner

@ExperimentalCoroutinesApi
@RunWith(PowerMockRunner::class)
class AuthorsViewModelTest {

    @Mock
    val repository = mock(AuthorsRepository::class.java)

    @Spy
    val savedStateHandle = spy(SavedStateHandle())

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()

        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testSetStateEvent() = runBlockingTest {
        val authorsViewModel = AuthorsViewModel(repository, savedStateHandle)
        val authors = mutableListOf<Author>()
        authors.add(Author(1, "", "", "", "", 0f, 0f))
        val stateEvent = AuthorsViewModel.AuthorStateEvent.GetAuthorEvent
        val responseState = DataState.Success(authors)
        val flow = flow<DataState<List<Author>>> {
            emit(responseState)
        }
        whenever(repository.getAuthors()).thenReturn(flow)
        authorsViewModel.setStateEvent(stateEvent)
        verify(repository).getAuthors()
    }
}