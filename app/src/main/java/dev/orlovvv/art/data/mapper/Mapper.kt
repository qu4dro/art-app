package dev.orlovvv.art.data.mapper

import dev.orlovvv.art.data.api.model.ListPhotoResponseItem
import dev.orlovvv.art.domain.model.Photo
import dev.orlovvv.art.domain.model.User

fun ListPhotoResponseItem.mapToDomainPhoto(): Photo {
    return Photo(
        id = id,
        description = description ?: alt_description ?: "",
        created_at = created_at,
        color = color,
        image_url = urls?.small ?: "",
        likes = likes ?: 0,
        user = User(
            id = user.id,
            username = user.username ?: "",
            firstname = user.first_name ?: "",
            lastname = user.last_name ?: "",
            bio = user.bio ?: "",
            location = user.location ?: "",
            image_url_small = user.profile_image?.small ?: "",
            image_url_big = user.profile_image?.large ?: ""
        )
    )
}