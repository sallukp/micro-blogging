package me.salmon.microblog.cache.comment

import androidx.room.ColumnInfo
import androidx.room.Entity
import me.salmon.microblog.cache.CacheConstants.avatar_url_key
import me.salmon.microblog.cache.CacheConstants.body_key
import me.salmon.microblog.cache.CacheConstants.date_key
import me.salmon.microblog.cache.CacheConstants.email_key
import me.salmon.microblog.cache.CacheConstants.id_key
import me.salmon.microblog.cache.CacheConstants.post_id_key
import me.salmon.microblog.cache.CacheConstants.user_name_key


@Entity(tableName = "comment", primaryKeys = [id_key, post_id_key])
data class CommentCacheEntity (

    @ColumnInfo(name = id_key)
    var id: Int,

    @ColumnInfo(name = post_id_key)
    var postId: Int,

    @ColumnInfo(name = date_key)
    var date: String?,

    @ColumnInfo(name = body_key)
    var body: String?,

    @ColumnInfo(name = user_name_key)
    var userName: String?,

    @ColumnInfo(name = email_key)
    var email: String?,

    @ColumnInfo(name = avatar_url_key)
    var avatarUrl: String?
)