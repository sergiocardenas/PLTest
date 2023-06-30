package com.example.domain.usecase

import com.example.domain.model.CountryModel
import com.example.domain.repository.CountryRemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountryAllUseCaseImp @Inject constructor(
    val repository: CountryRemoteRepository
): GetCountryAllUseCase {
    override suspend fun getAllCountries(): Flow<List<CountryModel>> {
        return repository.getAllCountries()
    }
}