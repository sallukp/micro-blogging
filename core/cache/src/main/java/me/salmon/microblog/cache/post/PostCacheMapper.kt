package me.salmon.microblog.cache.post

import me.salmon.microblog.models.Post
import me.salmon.microblog.utils.EntityMapper
import javax.inject.Inject

class PostCacheMapper
@Inject
constructor() : EntityMapper<PostCacheEntity, Post> {
    override fun mapFromEntity(entity: PostCacheEntity) = Post(entity.id, entity.authorId, entity.date,
        entity.title, entity.body, entity.imageUrl)

    override fun mapToEntity(post: Post) = PostCacheEntity(post.id, post.authorId, post.date, post.title,
        post.body, post.imageUrl)

    override fun mapFromEntities(list: List<PostCacheEntity>) = list.map { mapFromEntity(it) }

    override fun mapToEntities(list: List<Post>) = list.map { mapToEntity(it) }
}