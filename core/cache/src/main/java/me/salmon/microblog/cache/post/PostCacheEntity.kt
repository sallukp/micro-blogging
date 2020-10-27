package me.salmon.microblog.cache.post

import androidx.room.ColumnInfo
import androidx.room.Entity
import me.salmon.microblog.cache.CacheConstants.author_id_key
import me.salmon.microblog.cache.CacheConstants.body_key
import me.salmon.microblog.cache.CacheConstants.date_key
import me.salmon.microblog.cache.CacheConstants.id_key
import me.salmon.microblog.cache.CacheConstants.image_url_key
import me.salmon.microblog.cache.CacheConstants.title_key

@Entity(tableName = "post", primaryKeys = [id_key, author_id_key])
data class PostCacheEntity (

    @ColumnInfo(name = id_key)
    var id: Int,

    @ColumnInfo(name = author_id_key)
    var authorId: Int,

    @ColumnInfo(name = date_key)
    var date: String?,

    @ColumnInfo(name = title_key)
    var title: String?,

    @ColumnInfo(name = body_key)
    var body: String?,

    @ColumnInfo(name = image_url_key)
    var imageUrl: String?

)