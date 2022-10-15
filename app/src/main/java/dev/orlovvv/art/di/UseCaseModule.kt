package dev.orlovvv.art.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.orlovvv.art.domain.repository.PhotoRepository
import dev.orlovvv.art.domain.usecase.FetchPhotosUseCase
import dev.orlovvv.art.domain.usecase.PhotoUseCases
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Singleton
    @Provides
    fun provideFetchPhotoUseCase(photoRepository: PhotoRepository): FetchPhotosUseCase = FetchPhotosUseCase(photoRepository)

    @Singleton
    @Provides
    fun providePhotoUseCases(fetchPhotosUseCase: FetchPhotosUseCase): PhotoUseCases = PhotoUseCases(fetchPhotosUseCase)

}