package dev.orlovvv.art.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.orlovvv.art.domain.usecase.PhotoUseCases
import dev.orlovvv.art.ui.fragments.home.HomeUiState
import dev.orlovvv.art.ui.fragments.home.mapToHomeUi
import dev.orlovvv.art.utils.LoadState
import dev.orlovvv.art.utils.Request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val photoUseCases: PhotoUseCases) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState
        get() = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    init {
        fetchPhotos()
    }

    fun fetchPhotos() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch(Dispatchers.IO) {
            photoUseCases.fetchPhotosUseCase.invoke().collect { request ->
                when (request) {
                    is Request.Error -> _uiState.update { it.copy(loadState = LoadState.ERROR) }
                    is Request.Loading -> _uiState.update { it.copy(loadState = LoadState.LOADING) }
                    is Request.Success -> {
                        val photos = request.data?.map { it.mapToHomeUi() } ?: listOf()
                        _uiState.update { it.copy(loadState = LoadState.SUCCESS, photos = photos) }
                    }
                }
            }
        }
    }

}