package me.salmon.microblog.models

import org.junit.Test

import org.junit.Assert.*

class AuthorTest {

    @Test
    fun testEquals() {
        val author0 = Author(1, "", "", "", "", 0f, 0f)
        val author1 = Author(2, "", "", "", "", 0f, 0f)
        val author2 = Author(1, "Michael Page", "", "", "", 0f, 0f)
        assertEquals(author0, author2)
        assertNotEquals(author0, author1)
        assertNotEquals(author1, author2)
    }

    @Test
    fun getFirstLetters() {
        var author = Author(1, "Michael Page", "", "", "", 0f, 0f)
        assertEquals("MP", author.getFirstLetters())

        author = Author(1, "David John Kottarathil", "", "", "", 0f, 0f)
        assertEquals("DJ", author.getFirstLetters())

        author = Author(1, "David", "", "", "", 0f, 0f)
        assertEquals("D", author.getFirstLetters())

        author = Author(1, "", "", "", "", 0f, 0f)
        assertEquals("", author.getFirstLetters())

        author = Author(1, null, "", "", "", 0f, 0f)
        assertEquals("", author.getFirstLetters())
    }
}