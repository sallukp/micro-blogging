package me.salmon.microblog.models

data class Post(val id: Int,
                val authorId: Int,
                val date: String?,
                val title: String?,
                val body: String?,
                val imageUrl: String?) {
}