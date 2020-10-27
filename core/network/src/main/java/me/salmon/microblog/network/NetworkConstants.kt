package me.salmon.microblog.network

object NetworkConstants {

    // url
    const val base_url = "https://sym-json-server.herokuapp.com/"
    const val authors_path = "authors"
    const val posts_path = "posts"
    const val comments_path = "comments"

    // author keys
    const val id_key = "id"
    const val name_key = "name"
    const val user_name_key = "userName"
    const val email_key = "email"
    const val avatar_url_key = "avatarUrl"
    const val address_key = "address"

    // address keys
    const val lat_key = "latitude"
    const val long_key = "longitude"

    // post keys
    const val date_key = "date"
    const val title_key = "title"
    const val body_key = "body"
    const val image_url_key = "imageUrl"
    const val author_id_key = "authorId"

    // comment keys
    const val post_id_key = "postId"
}