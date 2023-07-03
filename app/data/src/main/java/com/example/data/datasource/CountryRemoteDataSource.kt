package com.example.data.datasource

import com.example.data.response.CountryDetailResponse
import com.example.data.response.CountryResponse
import kotlinx.coroutines.flow.Flow

interface CountryRemoteDataSource {
    suspend fun getAllCountries(): Flow<List<CountryResponse>>
    suspend fun getRegionCountries(region: String): Flow<List<CountryResponse>>
    suspend fun getSearchCountry(code: String): Flow<CountryDetailResponse?>
}