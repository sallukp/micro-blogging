package me.salmon.microblog.network.author

import me.salmon.microblog.models.Author
import me.salmon.microblog.utils.EntityMapper
import java.lang.Exception
import javax.inject.Inject

class AuthorNetworkMapper
@Inject
constructor(): EntityMapper<AuthorNetworkEntity, Author>{

    override fun mapFromEntity(entity: AuthorNetworkEntity): Author {
        var lat = 0f
        var long = 0f
        entity.address?.lat?.let {
            try { lat = it.toFloat() } catch (_ : Exception) {}
        }
        entity.address?.long?.let {
            try { long = it.toFloat() } catch (_ : Exception) {}
        }
        return Author(entity.id,
            entity.name,
            entity.userName,
            entity.email,
            entity.avatarUrl,
            lat,
            long)
    }

    override fun mapToEntity(domainModel: Author): AuthorNetworkEntity {
        TODO("Not yet implemented")
    }

    override fun mapFromEntities(list: List<AuthorNetworkEntity>) = list.map { mapFromEntity(it) }

}