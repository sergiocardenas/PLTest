package com.example.domain.model

data class CountryModel(
    val name : CountryNameModel,
    val cioc : String,
    val independent : Boolean,
    val status : String,
    val unMember : Boolean,
    val flags: CountryFlagModel,
)
