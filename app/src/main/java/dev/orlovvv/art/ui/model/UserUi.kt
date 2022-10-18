package dev.orlovvv.art.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserUi(
    val id: String,
    val username: String,
    val name: String,
    val bio: String,
    val location: String,
    val image_url_small: String,
    val image_url_big: String,
    val total_likes: String,
    val total_posts: String
): Parcelable