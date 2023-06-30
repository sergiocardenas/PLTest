package com.example.domain.mapper

import com.example.data.response.CountryDetailResponse
import com.example.data.response.CountryFlagResponse
import com.example.data.response.CountryNameResponse
import com.example.data.response.CountryResponse
import com.example.domain.model.CountryFlagModel
import com.example.domain.model.CountryModel
import com.example.domain.model.CountryNameModel

fun CountryNameResponse.toModel() = CountryNameModel(
    common = common,
    official = official,
)

fun CountryFlagResponse.toModel() = CountryFlagModel(
    png = png,
    svg = svg,
    alt = alt,
)

fun CountryResponse.toModel() = CountryModel(
    name = name.toModel(),
    cioc = cioc,
    independent = independent,
    status = status,
    unMember = unMember,
    flags = flags.toModel(),
)

fun CountryDetailResponse.toModel() = CountryModel(
    name = name.toModel(),
    cioc = cioc,
    independent = independent,
    status = status,
    unMember = unMember,
    flags = flags.toModel(),
)