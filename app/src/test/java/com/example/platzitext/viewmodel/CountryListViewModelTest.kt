package com.example.platzitext.viewmodel

import com.example.domain.usecase.GetCountryAllUseCase
import com.example.platzitext.state.CountryPageState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CountryListViewModelTest{
    @Mock
    private lateinit var useCase: GetCountryAllUseCase

    private lateinit var viewModel: CountryListViewModel

    private val regionList = listOf("America", "Asia", "Europa")

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

        viewModel.goNextPage()

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

        viewModel.goNextPage()

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

        viewModel.goPreviousPage()

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

        viewModel.goPreviousPage()

        Assert.assertEquals(viewModel.page.value.region, regionList[0])
        Assert.assertEquals(viewModel.page.value.hasNext, true)
        Assert.assertEquals(viewModel.page.value.hasPrevious, false)
    }
}