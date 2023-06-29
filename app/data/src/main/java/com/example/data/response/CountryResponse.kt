package com.example.data.response

data class CountryResponse(
    val name : CountryName,
    val cioc : String,
    val independent : Boolean,
    val status : String,
    val unMember : Boolean,
    val flags: CountryFlag,
)
