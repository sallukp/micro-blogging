package me.salmon.microblog.network.author

import me.salmon.microblog.network.NetworkConstants.author_path
import retrofit2.http.GET
import java.lang.Exception

interface AuthorsService {

    @Throws(Exception::class)
    @GET(author_path)
    suspend fun fetchAuthors(): List<AuthorNetworkEntity>
}