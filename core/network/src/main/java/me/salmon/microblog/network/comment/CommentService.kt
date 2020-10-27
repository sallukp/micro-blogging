package me.salmon.microblog.network.comment

import me.salmon.microblog.network.NetworkConstants.comments_path
import me.salmon.microblog.network.NetworkConstants.posts_path
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentService {

    @Throws
    @GET("${posts_path}/{post_id}/${comments_path}")
    suspend fun getComments(@Path("post_id") postId: Int,
                            @Query("_sort") sort: String = "date",
                            @Query("_order") order: String = "asc"): List<CommentNetworkEntity>
}