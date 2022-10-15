package dev.orlovvv.art.ui.fragments.home

import dev.orlovvv.art.domain.model.Photo
import dev.orlovvv.art.domain.model.User
import dev.orlovvv.art.utils.LoadState

data class HomeUiState(
    val loadState: LoadState = LoadState.SUCCESS,
    val photos: List<HomePhotoItemUiState> = listOf()
)

data class HomePhotoItemUiState(
    val description: String,
    val created_at: String,
    val image_url: String,
    val likes: String,
    val user: User
)

fun Photo.mapToHomeUi(): HomePhotoItemUiState {
    return HomePhotoItemUiState(
        description = description,
        created_at = created_at, // DATE FORMAT
        image_url = image_url.trim(),
        likes = likes.toString(),
        user = user
    )
}
