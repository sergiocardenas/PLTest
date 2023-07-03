package com.example.platzitext.viewmodel

import com.example.domain.model.CountryModel
import com.example.domain.usecase.GetRegionCountriesUseCase
import com.example.platzitext.mock.getCountryModelMock
import com.example.platzitext.mock.getCountryStateMock
import com.example.platzitext.state.CountryPageState
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
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CountryListViewModelTest{
    @Mock
    private lateinit var useCase: GetRegionCountriesUseCase

    private lateinit var viewModel: CountryListViewModel

    private val regionList = listOf("America", "Asia", "Europe")

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = CountryListViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testGoNextRegion(){
        viewModel.updatePage(
            index = 0,
            CountryPageState(
                region = regionList[0],
                hasNext = true,
                hasPrevious = false,
            )
        )

        val fetch = viewModel.goNextPage()

        Assert.assertEquals(fetch, true)
        Assert.assertEquals(viewModel.page.value.region, regionList[1])
        Assert.assertEquals(viewModel.page.value.hasNext, true)
        Assert.assertEquals(viewModel.page.value.hasPrevious, true)
    }
    @Test

    fun testNoGoNextRegion(){
        viewModel.updatePage(
            index = 2,
            CountryPageState(
                region = regionList[2],
                hasNext = false,
                hasPrevious = true,
            )
        )

        val fetch = viewModel.goNextPage()

        Assert.assertEquals(fetch, false)
        Assert.assertEquals(viewModel.page.value.region, regionList[2])
        Assert.assertEquals(viewModel.page.value.hasNext, false)
        Assert.assertEquals(viewModel.page.value.hasPrevious, true)
    }

    @Test
    fun testGoPreviousRegion(){
        viewModel.updatePage(
            index = 1,
            CountryPageState(
                region = regionList[1],
                hasNext = true,
                hasPrevious = true,
            )
        )

        val fetch = viewModel.goPreviousPage()

        Assert.assertEquals(fetch, true)
        Assert.assertEquals(viewModel.page.value.region, regionList[0])
        Assert.assertEquals(viewModel.page.value.hasNext, true)
        Assert.assertEquals(viewModel.page.value.hasPrevious, false)
    }
    @Test
    fun testNoGoPreviousRegion(){
        viewModel.updatePage(
            index = 0,
            CountryPageState(
                region = regionList[0],
                hasNext = true,
                hasPrevious = false,
            )
        )

        val fetch = viewModel.goPreviousPage()

        Assert.assertEquals(fetch, false)
        Assert.assertEquals(viewModel.page.value.region, regionList[0])
        Assert.assertEquals(viewModel.page.value.hasNext, true)
        Assert.assertEquals(viewModel.page.value.hasPrevious, false)
    }

    @Test
    fun testSearchRegionCountrySuccessful(): Unit = runBlocking {
        val region = "America"

        val searchResult = listOf(
            getCountryModelMock()
        )

        Mockito.`when`(useCase.getRegionCountries(region))
            .thenReturn(flowOf(searchResult))

        viewModel.searchCountryList(region)

        val countryState = getCountryStateMock()

        Assert.assertEquals(viewModel.list.value.size, 1)
        Assert.assertEquals(viewModel.list.value[0].name.common, countryState.name.common)
        Assert.assertEquals(viewModel.list.value[0].flags.png, countryState.flags.png)
        Assert.assertEquals(viewModel.list.value[0].cioc, countryState.cioc)
        Assert.assertEquals(viewModel.loading.value, false)
    }

    @Test
    fun testSearchRegionCountryFail(): Unit = runBlocking {
        val region = "America"

        val searchResult = listOf<CountryModel>()

        Mockito.`when`(useCase.getRegionCountries(region))
            .thenReturn(flowOf(searchResult))

        viewModel.searchCountryList(region)

        Assert.assertEquals(viewModel.list.value.size, 0)
        Assert.assertEquals(viewModel.loading.value, false)
    }
}