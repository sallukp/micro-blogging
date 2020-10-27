package me.salmon.microblog.cache.author

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.test.runBlockingTest
import me.salmon.microblog.cache.BlogCache
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CachedAuthorDao {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var cacheDatabase: BlogCache

    @Before
    fun initDb() {
        cacheDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            BlogCache::class.java).build()
    }

    @After
    fun closeDb() {
        cacheDatabase.close()
    }

    @Test
    fun insertAuthorsSavesData() = runBlockingTest{
        val authors = getDummyAuthors()
        cacheDatabase.authorDao().insertAuthors(authors)
        val cachedAuthors = cacheDatabase.authorDao().fetchAuthors()
        assert(cachedAuthors.isNotEmpty())
    }

    @Test
    fun getAuthorsRetrievesData() = runBlockingTest {
        val authors = getDummyAuthors()
        authors.forEach {
            cacheDatabase.authorDao().insertAuthor(it) }

        val retrieved = cacheDatabase.authorDao().fetchAuthors()
        assert(retrieved == authors)
    }

    private fun getDummyAuthors(): List<AuthorCacheEntity> {
        val entities = mutableListOf<AuthorCacheEntity>()
        entities.add(
            AuthorCacheEntity(1, "David John", "david", "david.john@me.com",
                "https://me.com/david.png", 43f, 44f)
        )
        entities.add(
            AuthorCacheEntity(2, "Michael Page", "michael", "michael.page@me.com",
            "https://me.com/michael.png", 43f, 44f)
        )
        entities.add(
            AuthorCacheEntity(3, "Grace Antony", "grace", "grace.antony@me.com",
            "https://me.com/grace.png", 43f, 44f)
        )
        return entities
    }

}