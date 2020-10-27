package me.salmon.microblog.network.comment

import me.salmon.microblog.models.Comment
import me.salmon.microblog.utils.EntityMapper
import javax.inject.Inject

class CommentNetworkMapper
@Inject
constructor(): EntityMapper<CommentNetworkEntity, Comment> {

    override fun mapFromEntity(entity: CommentNetworkEntity) = Comment(entity.id, entity.postId,
        entity.date, entity.body, entity.userName, entity.email, entity.avatarUrl)

    override fun mapToEntity(comment: Comment) = CommentNetworkEntity(comment.id, comment.postId,
        comment.date, comment.body, comment.userName, comment.email, comment.avatarUrl)

    override fun mapFromEntities(list: List<CommentNetworkEntity>) = list.map { mapFromEntity(it) }

    override fun mapToEntities(list: List<Comment>) = list.map { mapToEntity(it) }
}