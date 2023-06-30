package com.example.domain.usecase

import com.example.domain.model.CountryModel
import com.example.domain.repository.CountryRemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountrySearchUseCaseImp @Inject constructor(
    val repository: CountryRemoteRepository
): GetCountrySearchUseCase {
    override suspend fun getCountriesSearch(code: String): Flow<CountryModel> {
        return repository.getSearchCountry(code)
    }

}