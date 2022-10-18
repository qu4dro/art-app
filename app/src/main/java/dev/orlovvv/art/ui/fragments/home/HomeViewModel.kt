package dev.orlovvv.art.ui.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.orlovvv.art.domain.toUi
import dev.orlovvv.art.domain.usecase.PhotoUseCases
import dev.orlovvv.art.ui.fragments.home.HomeUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val photoUseCases: PhotoUseCases) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(photos = PagingData.empty()))
    val uiState
        get() = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            photoUseCases.fetchPhotosUseCase.invoke()
                .cachedIn(viewModelScope).collect { photos ->
                    _uiState.update { it.copy(photos = photos.map { photo -> photo.toUi() }) }
                }
        }
    }


}