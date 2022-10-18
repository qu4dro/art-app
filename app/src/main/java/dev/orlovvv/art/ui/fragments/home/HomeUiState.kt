package dev.orlovvv.art.ui.fragments.home

import androidx.paging.PagingData
import dev.orlovvv.art.ui.model.PhotoUi

data class HomeUiState(
    val photos: PagingData<PhotoUi>
)