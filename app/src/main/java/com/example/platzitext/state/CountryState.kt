package com.example.platzitext.state

data class CountryState(
    val name : CountryNameState,
    val cioc : String,
    val independent : Boolean,
    val status : String,
    val unMember : Boolean,
    val flags: CountryFlagState,
)
