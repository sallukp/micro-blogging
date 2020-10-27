package me.salmon.microblog.cache.comment

import me.salmon.microblog.models.Comment
import me.salmon.microblog.utils.EntityMapper
import javax.inject.Inject

class CommentCacheMapper
@Inject 
constructor(): EntityMapper<CommentCacheEntity, Comment>{

    override fun mapFromEntity(entity: CommentCacheEntity) = Comment(entity.id, entity.postId,
        entity.date, entity.body, entity.userName, entity.email, entity.avatarUrl)

    override fun mapToEntity(comment: Comment) = CommentCacheEntity(comment.id, comment.postId,
        comment.date, comment.body, comment.userName, comment.email, comment.avatarUrl)

    override fun mapFromEntities(list: List<CommentCacheEntity>) = list.map { mapFromEntity(it) }

    override fun mapToEntities(list: List<Comment>) = list.map { mapToEntity(it) }
}