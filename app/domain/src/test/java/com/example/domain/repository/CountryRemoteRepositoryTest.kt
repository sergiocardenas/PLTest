package com.example.domain.repository

import com.example.data.datasource.CountryRemoteDataSource
import com.example.data.response.CountryResponse
import com.example.domain.mock.getCountryDetailResponseMock
import com.example.domain.mock.getCountryModelMock
import com.example.domain.mock.getCountryResponseMock
import com.example.domain.mock.getEmptyCountryModelMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CountryRemoteRepositoryTest{
    @Mock
    private lateinit var dataSource: CountryRemoteDataSource
    private lateinit var repository: CountryRemoteRepository

    @Before
    fun setup() {
        repository =  CountryRemoteRepositoryImp(dataSource)
    }

    @Test
    fun testCountryList(): Unit = runBlocking {
        val listResponse = listOf(
            getCountryResponseMock()
        )

        Mockito.`when`(dataSource.getAllCountries()).thenReturn(flowOf(listResponse))

        val resultModel= getCountryModelMock()

        val countryList = repository.getAllCountries()

        countryList.collect{
            Assert.assertEquals(it.size, 1)
            Assert.assertEquals(it[0].cioc, resultModel.cioc)
            Assert.assertEquals(it[0].name.common, resultModel.name.common)
            Assert.assertEquals(it[0].flags.png, resultModel.flags.png)
        }
    }

    @Test
    fun testCountryListError(): Unit = runBlocking {
        val listResponse = listOf<CountryResponse>()

        Mockito.`when`(dataSource.getAllCountries()).thenReturn(flowOf(listResponse))

        val countryList = repository.getAllCountries()

        countryList.collect{
            Assert.assertEquals(it.size, 0)
        }
    }

    @Test
    fun testCountryRegionList(): Unit = runBlocking {
        val listResponse = listOf(
            getCountryResponseMock()
        )

        val region = "America"

        Mockito.`when`(dataSource.getRegionCountries(region)).thenReturn(flowOf(listResponse))

        val resultModel= getCountryModelMock()

        val countryList = repository.getRegionCountries(region)

        countryList.collect{
            Assert.assertEquals(it.size, 1)
            Assert.assertEquals(it[0].cioc, resultModel.cioc)
            Assert.assertEquals(it[0].name.common, resultModel.name.common)
            Assert.assertEquals(it[0].flags.png, resultModel.flags.png)
        }
    }

    @Test
    fun testCountryRegionListError(): Unit = runBlocking {
        val listResponse = listOf<CountryResponse>()

        val region = "America"

        Mockito.`when`(dataSource.getRegionCountries(region)).thenReturn(flowOf(listResponse))

        val countryList = repository.getRegionCountries(region)

        countryList.collect{
            Assert.assertEquals(it.size, 0)
        }
    }

    @Test
    fun testCountrySearch(): Unit = runBlocking {
        val listResponse = getCountryDetailResponseMock()
        val code = "COL"

        Mockito.`when`(dataSource.getSearchCountry(code)).thenReturn(flowOf(listResponse))

        val resultModel= getCountryModelMock()

        val countryList = repository.getSearchCountry(code)

        countryList.collect{
            Assert.assertEquals(it.cioc, resultModel.cioc)
            Assert.assertEquals(it.name.common, resultModel.name.common)
            Assert.assertEquals(it.flags.png, resultModel.flags.png)
        }
    }

    @Test
    fun testCountrySearchFail(): Unit = runBlocking {
        val listResponse = null
        val code = "COL"

        Mockito.`when`(dataSource.getSearchCountry(code)).thenReturn(flowOf(listResponse))

        val resultModel= getEmptyCountryModelMock()

        val countryList = repository.getSearchCountry(code)

        countryList.collect{
            Assert.assertEquals(it.cioc, resultModel.cioc)
            Assert.assertEquals(it.name.common, resultModel.name.common)
            Assert.assertEquals(it.flags.png, resultModel.flags.png)
        }
    }
}