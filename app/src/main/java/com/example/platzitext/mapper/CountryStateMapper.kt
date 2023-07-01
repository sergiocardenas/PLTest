package com.example.platzitext.mapper

import com.example.domain.model.CountryFlagModel
import com.example.domain.model.CountryModel
import com.example.domain.model.CountryNameModel
import com.example.platzitext.state.CountryFlagState
import com.example.platzitext.state.CountryNameState
import com.example.platzitext.state.CountryState

fun CountryNameModel.toState() = CountryNameState(
    common = common,
    official = official,
)

fun CountryFlagModel.toState() = CountryFlagState(
    png = png,
    svg = svg,
    alt = alt,
)

fun CountryModel.toState() = CountryState(
    name = name.toState(),
    cioc = cioc,
    independent = independent,
    status = status,
    unMember = unMember,
    flags = flags.toState(),
)
