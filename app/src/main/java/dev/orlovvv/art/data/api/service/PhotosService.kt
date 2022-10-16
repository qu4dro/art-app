package dev.orlovvv.art.data.api.service

import dev.orlovvv.art.BuildConfig
import dev.orlovvv.art.data.api.model.ListPhotoResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosService {

    @GET("photos/")
    suspend fun fetchPhotos(
        @Query("client_id") api_key: String = BuildConfig.API_KEY,
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 100
    ): List<ListPhotoResponseItem>

}