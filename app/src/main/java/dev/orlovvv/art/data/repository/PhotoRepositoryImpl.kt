package dev.orlovvv.art.data.repository

import dev.orlovvv.art.data.api.service.PhotosService
import dev.orlovvv.art.data.mapper.mapToDomainPhoto
import dev.orlovvv.art.domain.model.Photo
import dev.orlovvv.art.domain.repository.PhotoRepository
import dev.orlovvv.art.utils.Request
import dev.orlovvv.art.utils.RequestUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val photosService: PhotosService) :
    PhotoRepository {

    override suspend fun fetchPhotos(): Flow<Request<List<Photo>>> {
        return RequestUtils.requestFlow {
            val response = photosService.fetchPhotos()
            response.map { it.mapToDomainPhoto() }
        }
    }

}