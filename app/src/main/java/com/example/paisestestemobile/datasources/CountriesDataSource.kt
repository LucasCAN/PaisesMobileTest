package com.example.paisestestemobile.datasources

import com.example.paisestestemobile.network.ApiService
import javax.inject.Inject

class CountriesDataSource  @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getCountry(search: String) = apiService.getCountry(search)
    suspend fun getCountryByLanguage(search: String) = apiService.getCountryByLanguage(search)
}