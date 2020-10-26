package me.salmon.microblog.cache.author

import me.salmon.microblog.models.Author
import me.salmon.microblog.utils.EntityMapper
import javax.inject.Inject

class AuthorCacheMapper
@Inject
constructor() : EntityMapper<AuthorCacheEntity, Author>{

    override fun mapFromEntity(entity: AuthorCacheEntity) = Author(entity.id, entity.name, entity.username,
        entity.email, entity.avatarUrl, entity.lat, entity.long)

    override fun mapToEntity(author: Author) = AuthorCacheEntity(author.id, author.name, author.userName,
        author.email, author.avatarUrl, author.lat, author.long)

    override fun mapFromEntities(list: List<AuthorCacheEntity>) = list.map { mapFromEntity(it) }

    override fun mapToEntities(list: List<Author>) = list.map { mapToEntity(it) }
}