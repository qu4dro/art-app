package dev.orlovvv.art.domain.model

data class User(
    val id: String,
    val username: String,
    val name: String,
    val bio: String,
    val location: String,
    val image_url_small: String,
    val image_url_big: String,
    val total_likes: Int,
    val total_posts: Int
)