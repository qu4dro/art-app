package dev.orlovvv.art.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.orlovvv.art.domain.usecase.PhotoUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val photoUseCases: PhotoUseCases) : ViewModel() {

    private var fetchJob: Job? = null

    fun fetchPhotos() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch(Dispatchers.IO) {
            photoUseCases.fetchPhotosUseCase.invoke().collect {

            }
        }
    }

}