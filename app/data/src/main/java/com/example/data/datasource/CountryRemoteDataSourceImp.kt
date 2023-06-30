package com.example.data.datasource

import com.example.data.response.CountryDetailResponse
import com.example.data.response.CountryResponse
import com.example.data.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CountryRemoteDataSourceImp @Inject constructor(
    private val service: ApiService
): CountryRemoteDataSource {
    override suspend fun getAllCountries(): Flow<List<CountryResponse>> = flow {
        val list = withContext(Dispatchers.IO) {
            var searchList: List<CountryResponse> = listOf()
            val result = service.getAllCountries()
            if(result.isSuccessful){
                result.body()?.let {
                    searchList = it
                }
            }
            searchList
        }
        emit(list)
    }

    override suspend fun getSearchCountry(code: String): Flow<CountryDetailResponse?> = flow {
        val country = withContext(Dispatchers.IO) {
            var searchCountry: List<CountryDetailResponse> = listOf()
            if(code.isNotEmpty()){
                val result = service.getSearchCountries(code)
                if(result.isSuccessful){
                    result.body()?.let {
                        searchCountry = it
                    }
                }
                if(searchCountry.isNotEmpty()){
                    searchCountry[0]
                }else{
                    null
                }
            }else{
                null
            }
        }
        emit(country)
    }
}