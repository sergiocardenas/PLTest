package com.example.data.response

data class CountryResponse(
    val name : CountryNameResponse,
    val cioc : String?,
    val independent : Boolean,
    val status : String,
    val unMember : Boolean,
    val flags: CountryFlagResponse?,
)
