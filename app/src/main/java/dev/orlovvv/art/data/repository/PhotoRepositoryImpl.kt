package dev.orlovvv.art.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.orlovvv.art.data.api.paging.PhotosPageSource
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

//    override suspend fun fetchPhotos(): Flow<Request<PagingData<Photo>>> {
//        return RequestUtils.requestFlow {
//            val response = photosService.fetchPhotos()
//            response.map { it.mapToDomainPhoto() }
//        }
//    }

    override suspend fun fetchPhotos() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = { PhotosPageSource(photosService) }
    ).flow


}