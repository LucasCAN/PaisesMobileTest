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
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
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
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go back")
                    }
                }
            )
        },
        content = {
            CountryDetailContent(viewModel, country, it)
        },
    )
}

@Composable
fun CountryDetailContent(viewModel: CountriesViewModel, country: Country?, it: PaddingValues) {
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

                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.Gray, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Common name",
                    fontSize = 18.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(5.dp).fillMaxWidth()
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = country?.name?.common ?: "-",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.Gray, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Official name",
                    fontSize = 18.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(5.dp).fillMaxWidth()
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = country?.name?.official ?: "-",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.Gray, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Area",
                    fontSize = 18.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(5.dp).fillMaxWidth()
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = formatArea(country?.area) + " km²",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.Gray, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Population",
                    fontSize = 18.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(5.dp).fillMaxWidth()
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = formatPopulation(country?.population) ,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.Gray, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Continents",
                    fontSize = 18.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(5.dp).fillMaxWidth()
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = country?.continents?.joinToString(", ") ?: "-",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.Gray, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Languages",
                    fontSize = 18.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(5.dp).fillMaxWidth()
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = country?.languages?.values?.toList()?.joinToString(", ") ?: "-",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.Gray, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}