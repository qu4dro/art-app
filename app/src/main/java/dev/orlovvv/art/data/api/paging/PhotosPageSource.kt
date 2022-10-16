package dev.orlovvv.art.data.api.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.orlovvv.art.data.api.service.PhotosService
import dev.orlovvv.art.data.api.service.PhotosService.Companion.MAX_PAGE_SIZE
import dev.orlovvv.art.data.mapper.mapToDomainPhoto
import dev.orlovvv.art.domain.model.Photo
import retrofit2.HttpException
import javax.inject.Inject

class PhotosPageSource @Inject constructor(private val photosService: PhotosService) :
    PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {

        try {
            val page: Int = params.key ?: 1
            val perPage: Int = params.loadSize.coerceAtMost(MAX_PAGE_SIZE)

            val response = photosService.fetchPhotos(page = page, per_page = perPage)
            if (response.isSuccessful) {
                val photos = checkNotNull(response.body()).map { it.mapToDomainPhoto() }
                val nextKey = if (photos.size < perPage) null else page + 1
                val prevKey = if (page == 1) null else page - 1
                return LoadResult.Page(photos, prevKey, nextKey)
            } else {
                return LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

    }
}