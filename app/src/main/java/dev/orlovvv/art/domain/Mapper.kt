package dev.orlovvv.art.domain

import dev.orlovvv.art.domain.model.Advertising
import dev.orlovvv.art.domain.model.Photo
import dev.orlovvv.art.domain.model.User
import dev.orlovvv.art.ui.model.AdvertisingUi
import dev.orlovvv.art.ui.model.PhotoUi
import dev.orlovvv.art.ui.model.UserUi
import dev.orlovvv.art.utils.DateUtils

fun Photo.toUi(): PhotoUi {
    return PhotoUi(
        description = description,
        created_at = DateUtils.formatDate(created_at),
        color = color,
        image_url_small = image_url_small,
        image_url_large = image_url_large,
        likes = likes.toString(),
        user = user.toUi(),
        advertising = advertising?.toUi()
    )
}

fun User.toUi(): UserUi {
    return UserUi(
        id = id,
        username = username,
        name = name,
        bio = bio,
        location = location,
        image_url_small = image_url_small,
        image_url_big = image_url_big,
        total_likes = total_likes.toString(),
        total_posts = total_posts.toString(),
    )
}

fun Advertising.toUi(): AdvertisingUi {
    return AdvertisingUi(
        sponsor_name = sponsor_name,
        description = description,
        url = url
    )
}