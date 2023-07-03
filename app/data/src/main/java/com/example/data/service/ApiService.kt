package com.example.data.service

import com.example.data.RemoteConstants.GET_ALL_ENDPOINT
import com.example.data.RemoteConstants.REGION_ENDPOINT
import com.example.data.RemoteConstants.SEARCH_ENDPOINT
import com.example.data.response.CountryDetailResponse
import com.example.data.response.CountryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(GET_ALL_ENDPOINT)
    suspend fun getAllCountries(): Response<List<CountryResponse>>
    @GET("$REGION_ENDPOINT{region}")
    suspend fun getRegionCountries(@Path("region") code: String): Response<List<CountryResponse>>

    @GET("$SEARCH_ENDPOINT{code}")
    suspend fun getSearchCountries(@Path("code") code: String): Response<List<CountryDetailResponse>>
}