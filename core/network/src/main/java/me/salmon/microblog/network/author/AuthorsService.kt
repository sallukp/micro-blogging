package me.salmon.microblog.network.author

import me.salmon.microblog.network.NetworkConstants.author_path
import me.salmon.microblog.network.NetworkConstants.page_param
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthorsService {

    @GET(author_path)
    suspend fun fetchAuthors(@Query(page_param) page: String)
}