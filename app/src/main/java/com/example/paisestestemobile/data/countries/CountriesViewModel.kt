package com.example.paisestestemobile.data.countries

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paisestestemobile.repositories.CountriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
): ViewModel() {
    var countriesUIState = mutableStateOf(CountriesUIState())

    fun onEvent(event: CountriesUIEvent) {
        when(event) {
            is CountriesUIEvent.GetCountries -> {
                viewModelScope.launch {
                    countriesUIState.value = countriesUIState.value.copy(
                        countriesAreLoading = true,
                        countriesLoadingError = false,
                        allCountries = listOf()
                    )
                    val response = countriesRepository.getCountry(event.search.trim())

                    if (response.isSuccessful) {
                        countriesUIState.value = countriesUIState.value.copy(
                            allCountries = response.body() ?: emptyList(),
                            countriesAreLoading = false,
                            countriesLoadingError = false
                        )
                    } else {
                        countriesUIState.value = countriesUIState.value.copy(
                            allCountries = emptyList(),
                            countriesAreLoading = false,
                            countriesLoadingError = true
                        )
                    }
                }
            }
            is CountriesUIEvent.GetCountriesByLanguage -> {
                viewModelScope.launch {
                    countriesUIState.value = countriesUIState.value.copy(
                        countriesAreLoading = true,
                        countriesLoadingError = false,
                        allCountries = listOf()
                    )
                    val response = countriesRepository.getCountryByLanguage(event.search)

                    if (response.isSuccessful) {
                        countriesUIState.value = countriesUIState.value.copy(
                            allCountries = response.body() ?: emptyList(),
                            countriesAreLoading = false,
                            countriesLoadingError = false
                        )
                    } else {
                        countriesUIState.value = countriesUIState.value.copy(
                            allCountries = emptyList(),
                            countriesAreLoading = false,
                            countriesLoadingError = true
                        )
                    }
                }
            }
            is CountriesUIEvent.OpenCountryDetail -> {
                countriesUIState.value = countriesUIState.value.copy(
                    selectedCountry = event.country
                )
                event.onNavigate()
            }
        }
    }

}