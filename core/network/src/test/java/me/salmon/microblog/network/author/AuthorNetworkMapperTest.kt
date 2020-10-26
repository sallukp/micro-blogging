package me.salmon.microblog.network.author

import org.junit.Test

import org.junit.Assert.*

class AuthorNetworkMapperTest {

    @Test
    fun testMapFromEntity() {
        val address = AuthorNetworkEntity.Address("15.33", "55.42")
        val authorNetworkEntity = AuthorNetworkEntity(101,
            "David John",
            "david", "david.john@me.com", "https://me.com/david.png", address)
        val mapper = AuthorNetworkMapper()
        val author = mapper.mapFromEntity(authorNetworkEntity)
        assertEquals(authorNetworkEntity.id, author.id)
        assertEquals(authorNetworkEntity.name, author.name)
        assertEquals(authorNetworkEntity.userName, author.userName)
        assertEquals(authorNetworkEntity.email, author.email)
        assertEquals(authorNetworkEntity.avatarUrl, author.avatarUrl)
        assertEquals(authorNetworkEntity.address!!.lat!!.toFloat(), author.lat, 0.0f)
        assertEquals(authorNetworkEntity.address!!.long!!.toFloat(), author.long, 0.0f)
    }


    @Test
    fun testMapFromEntityWithAddresNull() {
        val address: AuthorNetworkEntity.Address? = null
        val authorNetworkEntity = AuthorNetworkEntity(101,
            "David John",
            "david", "david.john@me.com", "https://me.com/david.png", address)
        val mapper = AuthorNetworkMapper()
        val author = mapper.mapFromEntity(authorNetworkEntity)
        assertEquals(authorNetworkEntity.id, author.id)
        assertEquals(authorNetworkEntity.name, author.name)
        assertEquals(authorNetworkEntity.userName, author.userName)
        assertEquals(authorNetworkEntity.email, author.email)
        assertEquals(authorNetworkEntity.avatarUrl, author.avatarUrl)
        assertEquals(0.0f, author.lat, 0.0f)
        assertEquals(0.0f, author.long, 0.0f)
    }

    @Test
    fun testMapFromEntityWithCoordinatesNull() {
        val address = AuthorNetworkEntity.Address(null, null)
        val authorNetworkEntity = AuthorNetworkEntity(101,
            "David John",
            "david", "david.john@me.com", "https://me.com/david.png", address)
        val mapper = AuthorNetworkMapper()
        val author = mapper.mapFromEntity(authorNetworkEntity)
        assertEquals(authorNetworkEntity.id, author.id)
        assertEquals(authorNetworkEntity.name, author.name)
        assertEquals(authorNetworkEntity.userName, author.userName)
        assertEquals(authorNetworkEntity.email, author.email)
        assertEquals(authorNetworkEntity.avatarUrl, author.avatarUrl)
        assertEquals(0.0f, author.lat, 0.0f)
        assertEquals(0.0f, author.long, 0.0f)
    }


    @Test
    fun testMapFromEntityWithCoordinatesNaN() {
        val address = AuthorNetworkEntity.Address("I am NaN", "Me too")
        val authorNetworkEntity = AuthorNetworkEntity(101,
            "David John",
            "david", "david.john@me.com", "https://me.com/david.png", address)
        val mapper = AuthorNetworkMapper()
        val author = mapper.mapFromEntity(authorNetworkEntity)
        assertEquals(authorNetworkEntity.id, author.id)
        assertEquals(authorNetworkEntity.name, author.name)
        assertEquals(authorNetworkEntity.userName, author.userName)
        assertEquals(authorNetworkEntity.email, author.email)
        assertEquals(authorNetworkEntity.avatarUrl, author.avatarUrl)
        assertEquals(0.0f, author.lat, 0.0f)
        assertEquals(0.0f, author.long, 0.0f)
    }

    @Test
    fun testMapToEntity() {
        // Not implemented
    }

    @Test
    fun testMapFromEntities() {
        val entities = mutableListOf<AuthorNetworkEntity>()
        val authorNetworkEntity1 = AuthorNetworkEntity(101,
            "",
            "", "", "", null)
        val authorNetworkEntity2 = AuthorNetworkEntity(101,
            "",
            "", "", "", null)
        entities.add(authorNetworkEntity1)
        entities.add(authorNetworkEntity2)
        val mapper = AuthorNetworkMapper()
        val authors = mapper.mapFromEntities(entities)
        assertEquals(authorNetworkEntity1.id, authors[0].id)
        assertEquals(authorNetworkEntity2.id, authors[1].id)
    }

    @Test
    fun testMapToEntities() {
        // Not implemented
    }
}