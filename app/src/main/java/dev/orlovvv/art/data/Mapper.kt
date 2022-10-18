package dev.orlovvv.art.data
import dev.orlovvv.art.data.api.model.PhotoResponseDto
import dev.orlovvv.art.domain.model.Advertising
import dev.orlovvv.art.domain.model.Photo
import dev.orlovvv.art.domain.model.User

fun PhotoResponseDto.toDomain(): Photo {
    return Photo(
        id = id,
        description = description ?: alt_description ?: "",
        created_at = created_at,
        color = color,
        image_url_small = urls?.small ?: "",
        image_url_large = urls?.regular ?: "",
        likes = likes ?: 0,
        user = User(
            id = user.id,
            username = user.username ?: "",
            name = user.name ?: "",
            bio = user.bio ?: "",
            location = user.location ?: "",
            image_url_small = user.profile_image?.medium ?: "",
            image_url_big = user.profile_image?.large ?: "",
            total_likes = user.total_likes ?: 0,
            total_posts = user.total_photos ?: 0
        ),
        advertising = if (sponsorship == null) null else Advertising(
            description = sponsorship.tagline ?: "",
            url = sponsorship.sponsor?.portfolio_url ?: "",
            sponsor_name = sponsorship.sponsor?.name ?: ""
        )
    )
}