package dev.orlovvv.art.data.api.model

import com.squareup.moshi.Json

data class Social(
    @Json(name = "instagram_username")
    val instagram_username: String?,
    @Json(name = "paypal_email")
    val paypal_email: String?,
    @Json(name = "portfolio_url")
    val portfolio_url: String?,
    @Json(name = "twitter_username")
    val twitter_username: String?
)