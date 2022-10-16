package dev.orlovvv.art.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.orlovvv.art.domain.usecase.PhotoUseCases
import dev.orlovvv.art.ui.fragments.home.HomeUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val photoUseCases: PhotoUseCases) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(photos = PagingData.empty()))
    val uiState
        get() = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    init {
        fetchPhotos()
    }

    fun fetchPhotos() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch(Dispatchers.IO) {
            photoUseCases.fetchPhotosUseCase.invoke().collect { data ->
                _uiState.update { it.copy(photos = data) }
            }
        }
    }

}