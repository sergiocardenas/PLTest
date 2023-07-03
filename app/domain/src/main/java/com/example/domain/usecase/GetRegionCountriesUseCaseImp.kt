package com.example.domain.usecase

import com.example.domain.model.CountryModel
import com.example.domain.repository.CountryRemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRegionCountriesUseCaseImp @Inject constructor(
    val repository: CountryRemoteRepository
): GetRegionCountriesUseCase {
    override suspend fun getRegionCountries(region: String): Flow<List<CountryModel>> {
        return repository.getRegionCountries(region)
    }
}