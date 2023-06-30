package com.example.data.response

data class CountryDetailResponse(
    val name : CountryNameResponse,
    val cca2 : String,
    val ccn3 : String,
    val cca3 : String,
    val cioc : String,
    val independent : Boolean,
    val status : String,
    val unMember : Boolean,
    val flags: CountryFlagResponse,
)
