package com.example.data.mocks

import com.example.data.response.CountryDetailResponse
import com.example.data.response.CountryFlagResponse
import com.example.data.response.CountryNameResponse
import com.example.data.response.CountryResponse

fun getCountryResponseMock() = CountryResponse(
    name = CountryNameResponse(
        common = "Colombia",
        official = "Republic of Colombia",
    ),
    cioc = "COL",
    independent = true,
    status = "officially-assigned",
    unMember = false,
    flags = CountryFlagResponse(
        png =  "https://flagcdn.com/w320/co.png",
        svg = "https://flagcdn.com/co.svg",
        alt = "The flag of Colombia is composed of three horizontal bands of yellow, blue and red, with the yellow band twice the height of the other two bands."
    )
)
fun getCountryDetailResponseMock() = CountryDetailResponse(
    name = CountryNameResponse(
        common = "Colombia",
        official = "Republic of Colombia",
    ),
    cca2 = "COL",
    ccn3 = "COL",
    cca3 = "COL",
    cioc = "COL",
    independent = true,
    status = "officially-assigned",
    unMember = false,
    flags = CountryFlagResponse(
        png =  "https://flagcdn.com/w320/co.png",
        svg = "https://flagcdn.com/co.svg",
        alt = "The flag of Colombia is composed of three horizontal bands of yellow, blue and red, with the yellow band twice the height of the other two bands."
    )
)