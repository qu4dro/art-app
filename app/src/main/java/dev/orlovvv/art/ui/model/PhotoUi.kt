package dev.orlovvv.art.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoUi(
    val description: String,
    val created_at: String,
    val color: String,
    val image_url_small: String,
    val image_url_large: String,
    val likes: String,
    val user: UserUi,
    val advertising: AdvertisingUi? = null,
    val full_text: String = buildString {
        append(user.username)
        append("\n")
        if (description.isNotEmpty()) {
            append(description)
            append("\n")
        } else if (advertising?.description?.isNotEmpty() == true) {
            append(advertising.description)
            append("\n")
        }
        append(created_at)
        append("\n")
        append(color)
        append("\n")
        append(user.name)
    }
) : Parcelable
