package dev.orlovvv.art.domain.model

data class User(
    val id: String,
    val username: String,
    val firstname: String,
    val lastname: String,
    val bio: String,
    val location: String,
    val image_url_small: String,
    val image_url_big: String,
)