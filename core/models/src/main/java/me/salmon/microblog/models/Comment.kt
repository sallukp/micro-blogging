package me.salmon.microblog.models

data class Comment(val id: Int,
                   val postId: Int,
                   val date: String,
                   val body: String,
                   val userName: String,
                   val email: String,
                   val avatarUrl: String) {
}