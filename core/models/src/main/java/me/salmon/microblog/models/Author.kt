package me.salmon.microblog.models

data class Author(val id: Int,
                  val name: String?,
                  val userName: String?,
                  val email: String?,
                  val avatarUrl: String?,
                  val lat: Float,
                  val long: Float) {

}