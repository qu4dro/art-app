package dev.orlovvv.art.data.api.model

import com.squareup.moshi.Json

data class Sponsorship(
    @Json(name = "impression_urls")
    val impression_urls: List<String>?,
    @Json(name = "sponsor")
    val sponsor: Sponsor?,
    @Json(name = "tagline")
    val tagline: String?,
    @Json(name = "tagline_url")
    val tagline_url: String?
)