package com.example.domain.repository

import com.example.data.datasource.CountryRemoteDataSource
import com.example.domain.mapper.toModel
import com.example.domain.model.CountryFlagModel
import com.example.domain.model.CountryModel
import com.example.domain.model.CountryNameModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CountryRemoteRepositoryImp @Inject constructor(
    private val remoteDataSource: CountryRemoteDataSource
): CountryRemoteRepository {
    override suspend fun getAllCountries(): Flow<List<CountryModel>> {
        return remoteDataSource.getAllCountries().map { listAll ->
            listAll.map { it.toModel() }
        }
    }

    override suspend fun getSearchCountry(code: String): Flow<CountryModel> {
        return remoteDataSource.getSearchCountry(code).map { countryRes ->
            var country = CountryModel(
                name = CountryNameModel(
                    common = "", official = ""
                ),
                cioc = "",
                independent = false,
                status = "",
                unMember = false,
                flags = CountryFlagModel(
                    png = "", svg = "", alt = ""
                )
            )

            countryRes?.let{
                country = it.toModel()
            }

            country
        }
    }
}