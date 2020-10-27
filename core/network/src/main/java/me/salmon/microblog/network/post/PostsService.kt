package me.salmon.microblog.network.post

import me.salmon.microblog.network.NetworkConstants.author_id_key
import me.salmon.microblog.network.NetworkConstants.posts_path
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.Exception

interface PostsService {

    @Throws(Exception::class)
    @GET(posts_path)
    suspend fun getPosts(@Query(author_id_key) authorId: Int): List<PostNetworkEntity>
}