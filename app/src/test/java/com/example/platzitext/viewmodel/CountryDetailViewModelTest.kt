package com.example.platzitext.viewmodel

import com.example.domain.usecase.GetCountrySearchUseCase
import com.example.platzitext.mock.getCountryModelMock
import com.example.platzitext.mock.getCountryStateMock
import com.example.platzitext.mock.getEmptyCountryModelMock
import com.example.platzitext.mock.getEmptyCountryStateMock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CountryDetailViewModelTest{
    @Mock
    private lateinit var useCase: GetCountrySearchUseCase

    private lateinit var viewModel: CountryDetailViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = CountryDetailViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testSearchCountrySuccessful(): Unit = runBlocking {
        val countryCode = "COL"

        val searchResult = getCountryModelMock()

        Mockito.`when`(useCase.getCountriesSearch(countryCode))
            .thenReturn(flowOf(searchResult))

        viewModel.searchCountry(countryCode)

        val countryState = getCountryStateMock()

        Assert.assertEquals(viewModel.country.value.name.common, countryState.name.common)
        Assert.assertEquals(viewModel.country.value.flags.png, countryState.flags.png)
        Assert.assertEquals(viewModel.country.value.cioc, countryState.cioc)
        Assert.assertEquals(viewModel.loading.value, false)
    }

    @Test
    fun testSearchCountryError(): Unit = runBlocking {
        val countryCode = "COL"

        val searchResult = getEmptyCountryModelMock()

        Mockito.`when`(useCase.getCountriesSearch(countryCode))
            .thenReturn(flowOf(searchResult))

        viewModel.searchCountry(countryCode)

        val countryState = getEmptyCountryStateMock()

        Assert.assertEquals(viewModel.country.value.name.common, countryState.name.common)
        Assert.assertEquals(viewModel.country.value.flags.png, countryState.flags.png)
        Assert.assertEquals(viewModel.country.value.cioc, countryState.cioc)
        Assert.assertEquals(viewModel.loading.value, false)
    }
}