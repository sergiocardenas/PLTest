package com.example.platzitext.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetCountryAllUseCase
import com.example.platzitext.mapper.toState
import com.example.platzitext.state.CountryPageState
import com.example.platzitext.state.CountryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val useCase: GetCountryAllUseCase
): ViewModel() {

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> = _loading

    private val _list = MutableStateFlow<List<CountryState>>(mutableListOf())
    val list: StateFlow<List<CountryState>> = _list

    private val _regionlist = MutableStateFlow<List<String>>(mutableListOf("America", "Asia", "Europa"))
    private val _index = MutableStateFlow<Int>(0)

    private val _page = MutableStateFlow<CountryPageState>(updatePage())
    val page: StateFlow<CountryPageState> = _page

    init {
        searchCountryList()
    }

    fun searchCountryList(){
        viewModelScope.launch {
            _loading.value = true
            useCase.getAllCountries().collect{ listResult ->
                _loading.value = false
                if(listResult.isNotEmpty()){
                    _list.value = listResult.map { it.toState() }
                }else{
                    _list.value = mutableListOf()
                }
            }
        }
    }

    private fun hasNext() = _index.value < _regionlist.value.size-1

    private fun hasPrevious() = _index.value > 0

    private fun getRegion() = _regionlist.value[_index.value]

    private fun updatePage() = CountryPageState(
        region = getRegion(),
        hasNext = hasNext(),
        hasPrevious = hasPrevious()
    )

    fun goNextPage(){
        if(_page.value.hasNext){
            _index.value = _index.value+1
            _page.value = updatePage()
        }
    }
    fun goPreviousPage(){
        if(_page.value.hasPrevious){
            _index.value = _index.value-1
            _page.value = updatePage()
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun updatePage(index: Int, page: CountryPageState){
        _index.value = index
        _page.value = page
    }
}
