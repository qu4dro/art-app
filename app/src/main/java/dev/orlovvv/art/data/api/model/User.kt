package dev.orlovvv.art.data.api.model

import com.squareup.moshi.Json

data class User(
    @Json(name = "accepted_tos")
    val accepted_tos: Boolean?,
    @Json(name = "bio")
    val bio: String?,
    @Json(name = "first_name")
    val first_name: String?,
    @Json(name = "for_hire")
    val for_hire: Boolean?,
    @Json(name = "id")
    val id: String,
    @Json(name = "instagram_username")
    val instagram_username: String?,
    @Json(name = "last_name")
    val last_name: String?,
    @Json(name = "links")
    val links: LinksX?,
    @Json(name = "location")
    val location: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "portfolio_url")
    val portfolio_url: String?,
    @Json(name = "profile_image")
    val profile_image: ProfileImage?,
    @Json(name = "social")
    val social: Social?,
    @Json(name = "total_collections")
    val total_collections: Int?,
    @Json(name = "total_likes")
    val total_likes: Int?,
    @Json(name = "total_photos")
    val total_photos: Int?,
    @Json(name = "twitter_username")
    val twitter_username: String?,
    @Json(name = "updated_at")
    val updated_at: String?,
    @Json(name = "username")
    val username: String?
)