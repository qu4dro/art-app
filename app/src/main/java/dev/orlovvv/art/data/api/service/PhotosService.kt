package dev.orlovvv.art.data.api.service

import dev.orlovvv.art.BuildConfig
import dev.orlovvv.art.data.api.model.PhotoResponseDto
import dev.orlovvv.art.data.api.paging.MAX_PAGE_SIZE
import dev.orlovvv.art.data.api.paging.STARTING_PAGE_INDEX
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosService {

    @GET("photos/")
    suspend fun fetchPhotos(
        @Query("client_id") api_key: String = BuildConfig.API_KEY,
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("per_page") per_page: Int = MAX_PAGE_SIZE
    ): Response<List<PhotoResponseDto>>

}