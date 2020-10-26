package me.salmon.microblog.network.author

import com.google.gson.annotations.SerializedName
import me.salmon.microblog.network.NetworkConstants.address_key
import me.salmon.microblog.network.NetworkConstants.avatar_url_key
import me.salmon.microblog.network.NetworkConstants.email_key
import me.salmon.microblog.network.NetworkConstants.id_key
import me.salmon.microblog.network.NetworkConstants.lat_key
import me.salmon.microblog.network.NetworkConstants.long_key
import me.salmon.microblog.network.NetworkConstants.name_key
import me.salmon.microblog.network.NetworkConstants.user_name_key

data class AuthorNetworkEntity(

    @SerializedName(id_key)
    var id: Int,

    @SerializedName(name_key)
    var name: String?,

    @SerializedName(user_name_key)
    var userName: String?,

    @SerializedName(email_key)
    var email: String?,

    @SerializedName(avatar_url_key)
    var avatarUrl: String?,

    @SerializedName(address_key)
    var address: Address?)

data class Address (

    @SerializedName(lat_key)
    var lat: String?,

    @SerializedName(long_key)
    var long: String?)