package com.example.domain.di

import com.example.data.datasource.CountryRemoteDataSource
import com.example.domain.repository.CountryRemoteRepository
import com.example.domain.repository.CountryRemoteRepositoryImp
import com.example.domain.usecase.GetCountryAllUseCase
import com.example.domain.usecase.GetCountryAllUseCaseImp
import com.example.domain.usecase.GetCountrySearchUseCase
import com.example.domain.usecase.GetCountrySearchUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Singleton
    @Provides
    fun provideMLRepository(remote: CountryRemoteDataSource): CountryRemoteRepository =
        CountryRemoteRepositoryImp(remote)

    @Singleton
    @Provides
    fun provideCountryListUseCase(repository: CountryRemoteRepository): GetCountryAllUseCase =
        GetCountryAllUseCaseImp(repository)

    @Singleton
    @Provides
    fun provideCountrySearchUseCase(repository: CountryRemoteRepository): GetCountrySearchUseCase =
        GetCountrySearchUseCaseImp(repository)
}