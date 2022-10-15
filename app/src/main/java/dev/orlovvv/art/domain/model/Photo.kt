package dev.orlovvv.art.domain.model

data class Post(
    val id: String,
    val description: String,
    val created_at: String,
    val color: String,
    val image_url: String,
    val likes: Int,
    val user: User
)