package dev.orlovvv.art.domain.model

import dev.orlovvv.art.ui.model.PhotoUi
import dev.orlovvv.art.utils.DateUtils

data class Photo(
    val id: String,
    val description: String,
    val created_at: String,
    val color: String,
    val image_url_small: String,
    val image_url_large: String,
    val likes: Int,
    val user: User,
    val advertising: Advertising? = null
)