package dev.orlovvv.art.domain.usecase

import dev.orlovvv.art.domain.repository.PhotoRepository
import javax.inject.Inject

class FetchPhotosUseCase @Inject constructor(private val photoRepository: PhotoRepository) {

    operator fun invoke() = photoRepository.fetchPhotos()

}