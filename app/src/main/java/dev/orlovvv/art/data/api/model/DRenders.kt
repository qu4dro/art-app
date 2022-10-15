package dev.orlovvv.art.data.api.model

import com.squareup.moshi.Json

data class DRenders(
    @Json(name = "status")
    val status: String?
)