package dev.orlovvv.art.ui.model

data class PhotoUi(
    val description: String,
    val created_at: String,
    val color: String,
    val image_url_small: String,
    val image_url_large: String,
    val likes: String,
    val user: UserUi,
    val advertising: AdvertisingUi? = null
)
