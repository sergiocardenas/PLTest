package com.example.domain.usecase

import com.example.domain.model.CountryModel
import kotlinx.coroutines.flow.Flow

interface GetRegionCountriesUseCase {
    suspend fun getRegionCountries(region: String): Flow<List<CountryModel>>
}