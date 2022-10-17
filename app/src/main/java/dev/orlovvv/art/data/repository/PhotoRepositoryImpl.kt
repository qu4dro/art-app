package dev.orlovvv.art.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import dev.orlovvv.art.data.api.paging.MAX_PAGE_SIZE
import dev.orlovvv.art.data.api.paging.PhotosPagingSource
import dev.orlovvv.art.data.api.paging.STARTING_PAGE_INDEX
import dev.orlovvv.art.data.api.service.PhotosService
import dev.orlovvv.art.data.mapper.mapToDomainPhoto
import dev.orlovvv.art.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val photosService: PhotosService) :
    PhotoRepository {

//    override suspend fun fetchPhotos(): Flow<Request<PagingData<Photo>>> {
//        return RequestUtils.requestFlow {
//            val response = photosService.fetchPhotos()
//            response.map { it.mapToDomainPhoto() }
//        }
//    }

    override fun fetchPhotos() = Pager(
        config = PagingConfig(
            pageSize = MAX_PAGE_SIZE,
            enablePlaceholders = false,
        ),
        initialKey = STARTING_PAGE_INDEX,
        pagingSourceFactory = { PhotosPagingSource(photosService) }
    ).flow.mapLatest {
        it.map { photo ->
            photo.mapToDomainPhoto()
        }
    }


}