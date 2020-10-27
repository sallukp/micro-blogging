package me.salmon.microblog.network.post

import me.salmon.microblog.models.Post
import me.salmon.microblog.utils.EntityMapper
import javax.inject.Inject

class PostNetworkMapper
@Inject
constructor(): EntityMapper<PostNetworkEntity, Post> {

    override fun mapFromEntity(entity: PostNetworkEntity) = Post(entity.id, entity.authorId,
        entity.date, entity.title, entity.body, entity.imageUrl)

    override fun mapToEntity(post: Post) = PostNetworkEntity(post.id, post.authorId,
        post.date, post.title, post.body, post.imageUrl)

    override fun mapFromEntities(list: List<PostNetworkEntity>) = list.map { mapFromEntity(it) }

    override fun mapToEntities(list: List<Post>) = list.map { mapToEntity(it) }
}