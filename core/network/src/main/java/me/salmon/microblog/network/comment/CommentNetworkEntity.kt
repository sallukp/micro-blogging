package me.salmon.microblog.network.comment

import com.google.gson.annotations.SerializedName
import me.salmon.microblog.network.NetworkConstants.avatar_url_key
import me.salmon.microblog.network.NetworkConstants.body_key
import me.salmon.microblog.network.NetworkConstants.date_key
import me.salmon.microblog.network.NetworkConstants.email_key
import me.salmon.microblog.network.NetworkConstants.id_key
import me.salmon.microblog.network.NetworkConstants.post_id_key
import me.salmon.microblog.network.NetworkConstants.user_name_key

data class CommentNetworkEntity(

    @SerializedName(id_key)
    var id: Int,

    @SerializedName(post_id_key)
    var postId: Int,

    @SerializedName(date_key)
    var date: String?,

    @SerializedName(body_key)
    var body: String?,

    @SerializedName(user_name_key)
    var userName: String?,

    @SerializedName(email_key)
    var email: String?,

    @SerializedName(avatar_url_key)
    var avatarUrl: String?
)