package dev.orlovvv.art.domain.repository

import dev.orlovvv.art.domain.model.Photo
import dev.orlovvv.art.utils.Request
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

    suspend fun fetchPhotos(): Flow<Request<List<Photo>>>

}