package com.example.platzitext.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetCountryAllUseCase
import com.example.platzitext.mapper.toState
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
    private val _list = MutableStateFlow<List<CountryState>>(mutableListOf())
    val list: StateFlow<List<CountryState>> = _list

    init {
        searchCountryList()
    }

    fun searchCountryList(){
        viewModelScope.launch {
            useCase.getAllCountries().collect{ listResult ->
                if(listResult.isNotEmpty()){
                    _list.value = listResult.map { it.toState() }
                }else{
                    _list.value = mutableListOf()
                }
            }
        }
    }
}
