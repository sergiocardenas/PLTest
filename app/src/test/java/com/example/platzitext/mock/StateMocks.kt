package com.example.platzitext.mock

import com.example.data.response.CountryDetailResponse
import com.example.data.response.CountryFlagResponse
import com.example.data.response.CountryNameResponse
import com.example.data.response.CountryResponse
import com.example.domain.model.CountryFlagModel
import com.example.domain.model.CountryModel
import com.example.domain.model.CountryNameModel
import com.example.platzitext.state.CountryFlagState
import com.example.platzitext.state.CountryNameState
import com.example.platzitext.state.CountryState

fun getCountryModelMock() = CountryModel(
    name = CountryNameModel(
        common = "Colombia",
        official = "Republic of Colombia",
    ),
    cioc = "COL",
    independent = true,
    status = "officially-assigned",
    unMember = false,
    flags = CountryFlagModel(
        png =  "https://flagcdn.com/w320/co.png",
        svg = "https://flagcdn.com/co.svg",
        alt = "The flag of Colombia is composed of three horizontal bands of yellow, blue and red, with the yellow band twice the height of the other two bands."
    )
)

fun getEmptyCountryModelMock() = CountryModel(
    name = CountryNameModel(
        common = "", official = ""
    ),
    cioc = "",
    independent = false,
    status = "",
    unMember = false,
    flags = CountryFlagModel(
        png = "", svg = "", alt = ""
    )
)

fun getCountryStateMock() = CountryState(
    name = CountryNameState(
        common = "Colombia",
        official = "Republic of Colombia",
    ),
    cioc = "COL",
    independent = true,
    status = "officially-assigned",
    unMember = false,
    flags = CountryFlagState(
        png =  "https://flagcdn.com/w320/co.png",
        svg = "https://flagcdn.com/co.svg",
        alt = "The flag of Colombia is composed of three horizontal bands of yellow, blue and red, with the yellow band twice the height of the other two bands."
    )
)

fun getEmptyCountryStateMock() = CountryState(
    name = CountryNameState(
        common = "", official = ""
    ),
    cioc = "",
    independent = false,
    status = "",
    unMember = false,
    flags = CountryFlagState(
        png = "", svg = "", alt = ""
    )
)

