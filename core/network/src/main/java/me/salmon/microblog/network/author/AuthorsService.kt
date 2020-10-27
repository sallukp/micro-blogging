package me.salmon.microblog.network.author

import me.salmon.microblog.network.NetworkConstants.authors_path
import retrofit2.http.GET
import java.lang.Exception

interface AuthorsService {

    @Throws(Exception::class)
    @GET(authors_path)
    suspend fun fetchAuthors(): List<AuthorNetworkEntity>
}