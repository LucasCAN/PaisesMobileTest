package com.example.paisestestemobile.data.countries

import com.example.paisestestemobile.model.Country

data class CountriesUIState(
    var selectedCountry: Country? = null,
    val allCountries: List<Country> = listOf(),
    var countriesAreLoading: Boolean = false,
    var countriesLoadingError: Boolean = false
)
