package com.example.domain.usecase

import com.example.domain.model.CountryModel
import kotlinx.coroutines.flow.Flow

interface GetCountryAllUseCase {
    suspend fun getAllCountries(): Flow<List<CountryModel>>
}