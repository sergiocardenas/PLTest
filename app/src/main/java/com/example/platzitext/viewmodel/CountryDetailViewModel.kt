package com.example.platzitext.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetCountrySearchUseCase
import com.example.platzitext.mapper.toState
import com.example.platzitext.state.CountryFlagState
import com.example.platzitext.state.CountryNameState
import com.example.platzitext.state.CountryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    private val useCase: GetCountrySearchUseCase
) : ViewModel() {

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> = _loading

    private val empty = CountryState(
        name = CountryNameState("",""),
        cioc = "",
        independent = false,
        status = "",
        unMember = false,
        flags = CountryFlagState("","",""),
    )
    private val _item = MutableStateFlow<CountryState>(empty)
    val item: StateFlow<CountryState> = _item

    fun searchCountry(code: String){
        _loading.value = true
        viewModelScope.launch {
            useCase.getCountriesSearch(code).collect{country ->
                _loading.value = false
                _item.value = country.toState()
            }
        }
    }
}