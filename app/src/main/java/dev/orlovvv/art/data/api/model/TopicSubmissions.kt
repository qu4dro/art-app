package dev.orlovvv.art.data.api.model

import com.squareup.moshi.Json

data class TopicSubmissions(
    @Json(name = "3d-renders")
    val renders: DRenders?
)