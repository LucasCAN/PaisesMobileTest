package com.example.paisestestemobile.network

import com.example.paisestestemobile.model.Country
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("name/{name}")
    suspend fun getCountry(
        @Path("name") name: String
    ): Response<List<Country>>

    @GET("lang/{language}")
    suspend fun getCountryByLanguage(
        @Path("language") language: String
    ): Response<List<Country>>
}