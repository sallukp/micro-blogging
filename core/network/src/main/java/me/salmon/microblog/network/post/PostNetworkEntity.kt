package me.salmon.microblog.network.post

import com.google.gson.annotations.SerializedName
import me.salmon.microblog.network.NetworkConstants.author_id_key
import me.salmon.microblog.network.NetworkConstants.body_key
import me.salmon.microblog.network.NetworkConstants.date_key
import me.salmon.microblog.network.NetworkConstants.id_key
import me.salmon.microblog.network.NetworkConstants.image_url_key
import me.salmon.microblog.network.NetworkConstants.title_key

data class PostNetworkEntity(
    @SerializedName(id_key)
    var id: Int,

    @SerializedName(author_id_key)
    var authorId: Int,

    @SerializedName(date_key)
    var date: String?,

    @SerializedName(title_key)
    var title: String?,

    @SerializedName(body_key)
    var body: String?,

    @SerializedName(image_url_key)
    var imageUrl: String?
)