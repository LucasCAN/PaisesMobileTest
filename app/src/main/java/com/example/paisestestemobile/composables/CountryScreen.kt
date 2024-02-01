package com.example.paisestestemobile.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.example.paisestestemobile.R
import com.example.paisestestemobile.components.DividerSpacer
import com.example.paisestestemobile.data.countries.CountriesViewModel
import com.example.paisestestemobile.model.Country
import com.example.paisestestemobile.utils.formatArea
import com.example.paisestestemobile.utils.formatPopulation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryScreen(navController: NavHostController, viewModel: CountriesViewModel) {
    val country = viewModel.countriesUIState.value.selectedCountry

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = country?.name?.common ?: "-") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(
                                R.string.go_back
                            )
                        )
                    }
                }
            )
        },
        content = {
            CountryDetailContent(country, it)
        },
    )
}

@Composable
fun CountryDetailContent(country: Country?, it: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(it)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .background(Color.White)
                .padding(top = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            SubcomposeAsyncImage(
                model = country?.flag?.get("png"),
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(0.1.dp, Color.Black, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                DividerSpacer()

                Text(
                    text = stringResource(R.string.common_name),
                    fontSize = 18.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = country?.name?.common ?: "-",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                DividerSpacer()

                Text(
                    text = stringResource(R.string.official_name),
                    fontSize = 18.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = country?.name?.official ?: "-",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                DividerSpacer()

                Text(
                    text = stringResource(R.string.area),
                    fontSize = 18.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = formatArea(country?.area) + stringResource(R.string.km),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                DividerSpacer()

                Text(
                    text = stringResource(R.string.population),
                    fontSize = 18.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = formatPopulation(country?.population),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                DividerSpacer()

                Text(
                    text = stringResource(R.string.continents),
                    fontSize = 18.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = country?.continents?.joinToString(", ") ?: "-",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                DividerSpacer()

                Text(
                    text = stringResource(R.string.languages),
                    fontSize = 18.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = country?.languages?.values?.toList()?.joinToString(", ") ?: "-",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                DividerSpacer()
            }
        }
    }
}