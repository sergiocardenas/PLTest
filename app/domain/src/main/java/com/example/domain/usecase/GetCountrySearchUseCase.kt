package com.example.domain.usecase

import com.example.domain.model.CountryModel
import kotlinx.coroutines.flow.Flow

interface GetCountrySearchUseCase {
    suspend fun getCountriesSearch(code: String): Flow<CountryModel>
}