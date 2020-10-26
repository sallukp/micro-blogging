package me.salmon.microblog.cache.author

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.salmon.microblog.cache.CacheConstants.avatar_url_key
import me.salmon.microblog.cache.CacheConstants.email_key
import me.salmon.microblog.cache.CacheConstants.id_key
import me.salmon.microblog.cache.CacheConstants.lat_key
import me.salmon.microblog.cache.CacheConstants.long_key
import me.salmon.microblog.cache.CacheConstants.name_key
import me.salmon.microblog.cache.CacheConstants.user_name_key

@Entity(tableName = "author")
data class AuthorCacheEntity (

    @PrimaryKey
    @ColumnInfo(name = id_key)
    var id: Int,

    @ColumnInfo(name = name_key)
    var name: String?,

    @ColumnInfo(name = user_name_key)
    var username: String?,

    @ColumnInfo(name = email_key)
    var email: String?,

    @ColumnInfo(name = avatar_url_key)
    var avatarUrl: String?,

    @ColumnInfo(name = lat_key)
    var lat: Float,

    @ColumnInfo(name = long_key)
    var long: Float
) {
    constructor(id: Int):
        this(id, "", "", "", "", 0f, 0f)
}