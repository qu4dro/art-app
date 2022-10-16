package dev.orlovvv.art.ui.fragments.home

import dev.orlovvv.art.R
import dev.orlovvv.art.domain.model.Photo
import dev.orlovvv.art.domain.model.User
import dev.orlovvv.art.utils.DateUtils.formatDate
import dev.orlovvv.art.utils.LoadState

data class HomeUiState(
    val loadState: LoadState = LoadState.SUCCESS,
    val photos: List<HomePhotoItemUiState> = listOf()
)

data class HomePhotoItemUiState(
    val description: String,
    val created_at_date: String,
    val image_url: String,
    val likes: String,
    val user: User,
    val color: String
)

fun Photo.mapToHomeUi(): HomePhotoItemUiState {
    return HomePhotoItemUiState(
        description = description,
        created_at_date = formatDate(created_at),
        image_url = image_url,
        likes = likes.toString(),
        user = user,
        color = color
    )
}
