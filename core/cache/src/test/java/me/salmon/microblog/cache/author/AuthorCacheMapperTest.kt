package me.salmon.microblog.cache.author

import me.salmon.microblog.models.Author
import org.junit.Test

import org.junit.Assert.*

class AuthorCacheMapperTest {

    @Test
    fun testMapFromEntity() {
        val cacheEntity = AuthorCacheEntity(101,
            "David John",
            "david",
            "david.john@me.com",
            "https://me.com/davind.png",
            15.33f,
            44.32f)
        val mapper = AuthorCacheMapper()
        val author = mapper.mapFromEntity(cacheEntity)
        assertEquals(cacheEntity.id, author.id)
        assertEquals(cacheEntity.name, author.name)
        assertEquals(cacheEntity.username, author.userName)
        assertEquals(cacheEntity.email, author.email)
        assertEquals(cacheEntity.avatarUrl, author.avatarUrl)
        assertEquals(cacheEntity.lat, author.lat, 0.0f)
        assertEquals(cacheEntity.long, author.long, 0.0f)
    }

    @Test
    fun testMapToEntity() {
        val author = Author(101,
            "David John",
            "david",
            "david.john@me.com",
            "https://me.com/davind.png",
            15.33f,
            44.32f)
        val mapper = AuthorCacheMapper()
        val cacheEntity = mapper.mapToEntity(author)
        assertEquals(author.id, cacheEntity.id)
        assertEquals(author.name, cacheEntity.name)
        assertEquals(author.userName, cacheEntity.username)
        assertEquals(author.email, cacheEntity.email)
        assertEquals(author.avatarUrl, cacheEntity.avatarUrl)
        assertEquals(author.lat, cacheEntity.lat, 0.0f)
        assertEquals(author.long, cacheEntity.long, 0.0f)
    }


    @Test
    fun testMapFromEntities() {
        val entities = mutableListOf<AuthorCacheEntity>()
        val authorCacheEntity1 = AuthorCacheEntity(101,
            "",
            "", "", "", 0f, 0f)
        val authorCacheEntity2 = AuthorCacheEntity(101,
            "",
            "", "", "",  0f, 0f)
        entities.add(authorCacheEntity1)
        entities.add(authorCacheEntity2)
        val mapper = AuthorCacheMapper()
        val authors = mapper.mapFromEntities(entities)
        assertEquals(authorCacheEntity1.id, authors[0].id)
        assertEquals(authorCacheEntity2.id, authors[1].id)
    }

    @Test
    fun testMapToEntities() {
        val authors = mutableListOf<Author>()
        val author1 = Author(101,
            "",
            "", "", "", 0f, 0f)
        val author2 = Author(101,
            "",
            "", "", "",  0f, 0f)
        authors.add(author1)
        authors.add(author2)
        val mapper = AuthorCacheMapper()
        val entities = mapper.mapToEntities(authors)
        assertEquals(author1.id, entities[0].id)
        assertEquals(author2.id, entities[1].id)
    }
}