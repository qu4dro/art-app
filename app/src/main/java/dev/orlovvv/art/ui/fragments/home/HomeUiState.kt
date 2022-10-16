package dev.orlovvv.art.ui.fragments.home

import androidx.paging.PagingData
import dev.orlovvv.art.domain.model.Photo
import dev.orlovvv.art.domain.model.User
import dev.orlovvv.art.utils.DateUtils.formatDate
import dev.orlovvv.art.utils.LoadState

data class HomeUiState(
    val photos: PagingData<Photo>
)