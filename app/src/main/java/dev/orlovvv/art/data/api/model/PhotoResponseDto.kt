package dev.orlovvv.art.data.api.model

import com.squareup.moshi.Json

data class PhotoResponseDto(
    @Json(name = "alt_description")
    val alt_description: String?,
    @Json(name = "blur_hash")
    val blur_hash: String?,
    @Json(name = "color")
    val color: String,
    @Json(name = "created_at")
    val created_at: String,
    @Json(name = "current_user_collections")
    val current_user_collections: List<Any>?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "height")
    val height: Int?,
    @Json(name = "id")
    val id: String,
    @Json(name = "liked_by_user")
    val liked_by_user: Boolean?,
    @Json(name = "likes")
    val likes: Int?,
    @Json(name = "links")
    val links: Links?,
    @Json(name = "promoted_at")
    val promoted_at: String?,
    @Json(name = "sponsorship")
    val sponsorship: Sponsorship?,
    @Json(name = "topic_submissions")
    val topic_submissions: TopicSubmissions?,
    @Json(name = "updated_at")
    val updated_at: String?,
    @Json(name = "urls")
    val urls: Urls?,
    @Json(name = "user")
    val user: User,
    @Json(name = "width")
    val width: Int?
)