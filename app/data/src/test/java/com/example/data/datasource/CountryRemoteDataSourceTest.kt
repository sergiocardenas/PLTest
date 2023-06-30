package com.example.data.datasource

import com.example.data.mocks.getCountryDetailResponseMock
import com.example.data.mocks.getCountryResponseMock
import com.example.data.response.CountryDetailResponse
import com.example.data.response.CountryResponse
import com.example.data.service.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CountryRemoteDataSourceTest{
    @Mock
    private lateinit var service: ApiService

    private lateinit var dataSource: CountryRemoteDataSource

    @Before
    fun setup() {
        dataSource =  CountryRemoteDataSourceImp(service)
    }

    @Test
    fun testCountryList(): Unit = runBlocking {
        val listResponse = listOf(
            getCountryResponseMock()
        )

        val retrofitResponse = Response.success(listResponse)

        Mockito.`when`(service.getAllCountries()).thenReturn(retrofitResponse)

        val datasourceResult = dataSource.getAllCountries()
        val resultCountry = getCountryResponseMock()

        datasourceResult.collect{
            assertEquals(it.size, 1)
            assertEquals(it[0].name.common, resultCountry.name.common)
        }
    }

    @Test
    fun testCountryListFail(): Unit = runBlocking {
        val retrofitResponse = Response.error<List<CountryResponse>>(500, "Error".toResponseBody())

        Mockito.`when`(service.getAllCountries()).thenReturn(retrofitResponse)

        val datasourceResult = dataSource.getAllCountries()

        datasourceResult.collect{
            assertEquals(it.size, 0)
        }
    }

    @Test
    fun testCountrySearch(): Unit = runBlocking {
        val listResponse = listOf(
            getCountryDetailResponseMock()
        )

        val code = "COL"

        val retrofitResponse = Response.success(listResponse)

        Mockito.`when`(service.getSearchCountries(code)).thenReturn(retrofitResponse)

        val datasourceResult = dataSource.getSearchCountry(code)
        val resultCountry = getCountryResponseMock()

        datasourceResult.collect{
            assertEquals(it!!.name.common, resultCountry.name.common)
        }
    }

    @Test
    fun testCountrySearchFail(): Unit = runBlocking {
        val retrofitResponse = Response.error<List<CountryDetailResponse>>(500, "Error".toResponseBody())

        val code = "COL"

        Mockito.`when`(service.getSearchCountries(code)).thenReturn(retrofitResponse)

        val datasourceResult = dataSource.getSearchCountry(code)

        datasourceResult.collect{
            assertEquals(it, null)
        }
    }

    @Test
    fun testCountrySearchEmpty(): Unit = runBlocking {
        val listResponse = listOf(
            getCountryDetailResponseMock()
        )

        val code = ""

        val datasourceResult = dataSource.getSearchCountry(code)

        datasourceResult.collect{
            assertEquals(it, null)
        }
    }

}