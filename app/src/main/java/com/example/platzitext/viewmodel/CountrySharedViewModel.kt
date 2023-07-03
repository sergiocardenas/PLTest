package com.example.platzitext.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CountrySharedViewModel(): ViewModel() {
    private val _countryCode = MutableStateFlow<String?>(null)
    val countryCode: StateFlow<String?> = _countryCode

    fun clearState(){
        _countryCode.value = null
    }

    fun passCountry(code: String){
        _countryCode.value = code
    }
}