package me.salmon.microblog.network.author

import org.junit.Test

import org.junit.Assert.*

class AuthorNetworkMapperTest {

    @Test
    fun testMapFromEntity() {
        val address = Address("15.33", "55.42")
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
        assertEquals(authorNetworkEntity.address!!.lat!!.toFloat(), author.lat)
        assertEquals(authorNetworkEntity.address!!.long!!.toFloat(), author.long)
    }


    @Test
    fun testMapFromEntityWithAddresNull() {
        val address: Address? = null
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
        assertTrue(author.lat == 0f)
        assertTrue(author.long == 0f)
    }

    @Test
    fun testMapFromEntityWithCoordinatesNull() {
        val address = Address(null, null)
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
        assertTrue(author.lat == 0f)
        assertTrue(author.long == 0f)
    }


    @Test
    fun testMapFromEntityWithCoordinatesNaN() {
        val address = Address("I am NaN", "Me too")
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
        assertTrue(author.lat == 0f)
        assertTrue(author.long == 0f)
    }

    @Test
    fun testMapToEntity() {
        // No need to implement
    }
}