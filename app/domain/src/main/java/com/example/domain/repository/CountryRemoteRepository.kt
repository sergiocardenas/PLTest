package com.example.domain.repository

import com.example.domain.model.CountryModel
import kotlinx.coroutines.flow.Flow

interface CountryRemoteRepository {
    suspend fun getAllCountries(): Flow<List<CountryModel>>
    suspend fun getSearchCountry(code: String): Flow<CountryModel>
}
