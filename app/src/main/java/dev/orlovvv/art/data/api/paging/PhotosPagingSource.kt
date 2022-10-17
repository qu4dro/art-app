package dev.orlovvv.art.data.api.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.orlovvv.art.data.api.model.PhotoResponseDto
import dev.orlovvv.art.data.api.service.PhotosService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

const val STARTING_PAGE_INDEX = 1
const val MAX_PAGE_SIZE = 20

class PhotosPagingSource @Inject constructor(private val photosService: PhotosService) :
    PagingSource<Int, PhotoResponseDto>() {

    override fun getRefreshKey(state: PagingState<Int, PhotoResponseDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoResponseDto> {

        val position = params.key ?: STARTING_PAGE_INDEX
        val perPage: Int = params.loadSize.coerceAtMost(MAX_PAGE_SIZE)
        return try {
            val response = photosService.fetchPhotos(page = position, per_page = perPage)
            val photos = checkNotNull(response.body())
            val nextKey = if (photos.size < perPage) null else position + 1
            val prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1
            LoadResult.Page(
                data = photos,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }
//        try {
//            val page: Int = params.key ?: 1
//            val perPage: Int = params.loadSize.coerceAtMost(MAX_PAGE_SIZE)
//
//            val response = photosService.fetchPhotos(page = page, per_page = perPage)
//            if (response.isSuccessful) {
//                val photos = checkNotNull(response.body()).map { it.mapToDomainPhoto() }
//                val nextKey = if (photos.size < perPage) null else page + 1
//                val prevKey = if (page == 1) null else page - 1
//                return LoadResult.Page(photos, prevKey, nextKey)
//            } else {
//                return LoadResult.Error(HttpException(response))
//            }
//        } catch (e: HttpException) {
//            return LoadResult.Error(e)
//        } catch (e: Exception) {
//            return LoadResult.Error(e)
//        }
//
//    }
}