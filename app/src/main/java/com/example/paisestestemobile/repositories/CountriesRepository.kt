package com.example.paisestestemobile.repositories

import com.example.paisestestemobile.datasources.CountriesDataSource
import javax.inject.Inject

class CountriesRepository @Inject constructor(
    private val countriesDataSource: CountriesDataSource
) {
    suspend fun getCountry(search: String) = countriesDataSource.getCountry(search)
    suspend fun getCountryByLanguage(search: String) = countriesDataSource.getCountryByLanguage(search)
}