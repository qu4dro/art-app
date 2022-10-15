package dev.orlovvv.art.data.api.model

import com.squareup.moshi.Json

data class Links(
    @Json(name = "download")
    val download: String?,
    @Json(name = "download_location")
    val download_location: String?,
    @Json(name = "html")
    val html: String?,
    @Json(name = "self")
    val self: String?
)