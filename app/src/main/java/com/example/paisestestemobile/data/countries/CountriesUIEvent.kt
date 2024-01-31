package com.example.paisestestemobile.data.countries

import com.example.paisestestemobile.model.Country

sealed class CountriesUIEvent {
    class GetCountries(val search: String) : CountriesUIEvent()
    class GetCountriesByLanguage(val search: String) : CountriesUIEvent()
    data class OpenCountryDetail(val country: Country, val onNavigate: () -> Unit): CountriesUIEvent()
}