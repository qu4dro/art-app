package dev.orlovvv.art.domain.repository

import androidx.paging.PagingData
import dev.orlovvv.art.domain.model.Photo
import dev.orlovvv.art.utils.Request
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

    fun fetchPhotos(): Flow<PagingData<Photo>>

}