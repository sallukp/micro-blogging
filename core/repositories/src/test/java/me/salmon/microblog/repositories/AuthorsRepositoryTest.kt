package me.salmon.microblog.repositories

import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.test.runBlockingTest
import me.salmon.microblog.cache.author.AuthorCacheEntity
import me.salmon.microblog.cache.author.AuthorCacheMapper
import me.salmon.microblog.cache.author.AuthorDao
import me.salmon.microblog.models.Author
import me.salmon.microblog.network.author.AuthorNetworkEntity
import me.salmon.microblog.network.author.AuthorNetworkMapper
import me.salmon.microblog.network.author.AuthorsService
import me.salmon.microblog.utils.DataState
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Spy
import org.powermock.api.mockito.PowerMockito.mock
import org.powermock.modules.junit4.PowerMockRunner
import java.lang.Exception


@RunWith(PowerMockRunner::class)
class AuthorsRepositoryTest {

    @Mock
    val authorsService = mock(AuthorsService::class.java)

    @Mock
    val authorsDao = mock(AuthorDao::class.java)

    @Mock
    val networkMapper = mock(AuthorNetworkMapper::class.java)

    @Mock
    val cacheMapper = mock(AuthorCacheMapper::class.java)

    @Spy
    val authorsRepository = spy(AuthorsRepository(authorsService, authorsDao, networkMapper, cacheMapper))

    @Test
    fun testGetAuthors() {

    }

    @Test
    fun testGetAuthorsCache() = runBlockingTest {
        val entities = listOf<AuthorCacheEntity>()

        val authors = mutableListOf<Author>()
        val author = Author(101, "", "", "", "", 0f, 0f)
        val authorNotAdded = Author(102, "", "", "", "", 0f, 0f)
        authors.add(author)

        whenever(authorsDao.fetchAuthors()).thenReturn( entities )
        whenever(cacheMapper.mapFromEntities(any())).thenReturn( authors )
        val flowCollector = mock<FlowCollector<DataState<List<Author>>>>()

        authorsRepository.getAuthorsCache(flow = flowCollector);

        val captor = argumentCaptor<DataState.Success<List<Author>>>()
        verify(flowCollector).emit(captor.capture())
        assertEquals(author, captor.firstValue.data[0])
        assertNotEquals(authorNotAdded, captor.firstValue.data[0])
    }

    @Test
    fun testPrepareAuthorsCache() = runBlockingTest {
        whenever(authorsDao.insertAuthors(any())).then {  }
        val authors = listOf<Author>()

        val entities = mutableListOf<AuthorCacheEntity>()
        val entity = AuthorCacheEntity(101, "", "", "", "", 0f, 0f)
        val entityNotAdded = AuthorCacheEntity(102, "", "", "", "", 0f, 0f)
        entities.add(entity)

        whenever(cacheMapper.mapToEntities(authors)).thenReturn(entities)

        authorsRepository.prepareAuthorsCache(authors)

        val captor = argumentCaptor<List<AuthorCacheEntity>>()
        verify(authorsDao).insertAuthors(captor.capture())
        assertEquals(entity, captor.firstValue[0])
        assertNotEquals(entityNotAdded, captor.firstValue[0])
    }

    @Test(expected = Test.None::class)
    fun testGetAuthorsFromNetwork() = runBlockingTest {
        val entities = listOf<AuthorNetworkEntity>()

        val authors = mutableListOf<Author>()
        val author = Author(101, "", "", "", "", 0f, 0f)
        val authorNotAdded = Author(102, "", "", "", "", 0f, 0f)
        authors.add(author)

        whenever(authorsService.fetchAuthors()).thenReturn( entities )
        whenever(networkMapper.mapFromEntities(any())).thenReturn( authors )
        val flowCollector = mock<FlowCollector<DataState<List<Author>>>>()

        authorsRepository.getAuthorsFromNetwork(flow = flowCollector)

        verify(networkMapper).mapFromEntities(entities)

        verify(authorsService).fetchAuthors()
        
        val authorListCaptor = argumentCaptor<List<Author>>()
        verify(authorsRepository).prepareAuthorsCache(authorListCaptor.capture())
        assertEquals(author, authorListCaptor.firstValue[0])
        assertNotEquals(authorNotAdded, authorListCaptor.firstValue[0])


        val dataStateCaptor = argumentCaptor<DataState.Success<List<Author>>>()
        verify(flowCollector).emit(dataStateCaptor.capture())
        assertEquals(author, dataStateCaptor.firstValue.data[0])
        assertNotEquals(authorNotAdded, dataStateCaptor.firstValue.data[0])

        // test for exception
        val exception = Exception("test exception")
        whenever(authorsService.fetchAuthors()).thenThrow(exception)
        authorsRepository.getAuthorsFromNetwork(flow = flowCollector)
        val errorCapture = argumentCaptor<DataState.Error>()
        verify(flowCollector, times(2)).emit(errorCapture.capture())
        assertEquals(exception.localizedMessage, errorCapture.secondValue.exception.localizedMessage)

    }
}