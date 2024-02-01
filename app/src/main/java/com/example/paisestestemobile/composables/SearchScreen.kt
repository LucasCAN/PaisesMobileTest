package com.example.paisestestemobile.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.example.paisestestemobile.R
import com.example.paisestestemobile.components.ButtonComponent
import com.example.paisestestemobile.data.countries.CountriesUIEvent
import com.example.paisestestemobile.data.countries.CountriesViewModel
import com.example.paisestestemobile.model.Country
import com.example.paisestestemobile.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController, viewModel: CountriesViewModel) {
    val countries = viewModel.countriesUIState.value.allCountries
    val isLoading = viewModel.countriesUIState.value.countriesAreLoading
    val hasError = viewModel.countriesUIState.value.countriesLoadingError

    var text by remember {
        mutableStateOf("")
    }
    var iconClean by remember {
        mutableStateOf(false)
    }
    val languages = stringArrayResource(id = R.array.languages_array)
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SearchBar(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f),
                tonalElevation = 500.dp,
                query = text,
                onQueryChange = {
                    text = it
                },
                onSearch = {
                    viewModel.onEvent(CountriesUIEvent.GetCountries(it))
                },
                active = false,
                onActiveChange = {
                    iconClean = true
                },
                placeholder = {
                    Text(text = stringResource(R.string.search_country_name))
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.search_icon))
                },
                trailingIcon = {
                    if (iconClean) {
                        Icon(
                            modifier = Modifier.clickable {
                                if (text.isNotEmpty()) {
                                    text = ""
                                }
                            },
                            imageVector = Icons.Default.Close, contentDescription = stringResource(R.string.close_icon)
                        )
                    }
                }
            ) {}

            IconButton(modifier = Modifier
                .padding(start = 8.dp, top = 8.dp)
                .align(Alignment.CenterVertically)
                .border(1.dp, Color.LightGray, CircleShape),
                onClick = {
                    isSheetOpen = true
                }) {
                Icon(
                    modifier = Modifier
                        .size(35.dp),
                    imageVector = Icons.Default.FilterList, contentDescription = stringResource(R.string.filter)
                )
            }
        }

        Divider(color = Color.LightGray, modifier = Modifier.fillMaxWidth())

        //Controle do bottomsheet para filtrar por idiomas
        if (isSheetOpen) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = { isSheetOpen = false }) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    text = stringResource(R.string.search_countries_by_language),
                    textAlign = TextAlign.Center
                )
                Divider(color = Color.Gray, modifier = Modifier.fillMaxWidth())

                LazyColumn(
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                ) {
                    items(languages) {
                        Text(
                            text = it,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.onEvent(CountriesUIEvent.GetCountriesByLanguage(it))
                                    isSheetOpen = false
                                }
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 12.dp,
                                ),
                        )
                    }
                }

            }
        }

        if (isLoading) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        if (hasError) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(id = R.string.error_loading))
                ButtonComponent(
                    value = stringResource(id = R.string.retry),
                    onButtonClicked = {
                        viewModel.onEvent(CountriesUIEvent.GetCountries(text))
                    },
                    imageVector = Icons.Default.ArrowCircleDown,
                    isEnabled = true
                )
            }
        }

        if (!isLoading && !hasError) {
            CountryList(countries,
                onItemClick = { country ->
                    viewModel.onEvent(
                        CountriesUIEvent.OpenCountryDetail(
                            country,
                            onNavigate = {
                                navController.navigate(Screen.COUNTRY_SCREEN.name)
                            }
                        )
                    )
                })
        }
    }

}

@Composable
fun CountryList(countries: List<Country>, onItemClick: (Country) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(countries.chunked(2)) { countryList ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (country in countryList) {
                        Column(
                            Modifier
                                .weight(1f)
                                .padding(10.dp)
                        ) {
                            CountryItem(country = country, onItemClick)
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun CountryItem(country: Country, onItemClick: (Country) -> Unit) {
    Column(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .clickable(onClick = { onItemClick(country) })
    ) {
        Column {
            SubcomposeAsyncImage(
                model = country.flag?.get("png"),
                loading = {
                    Box{
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                },
                contentDescription = null,
                modifier = Modifier
                    .width(175.dp)
                    .height(100.dp)
                    .border(0.1.dp, Color.Black, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.Center,
            )
            Text(
                modifier = Modifier
                    .width(175.dp),
                text = country.name.common,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }
    }
}