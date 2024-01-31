package com.example.paisestestemobile.model

import com.google.gson.annotations.SerializedName

data class Country(
    val name: Name,
    val area: Double,
    val languages: Map<String, String>,
    val continents: List<String>,
    @SerializedName("flags")
    val flag: Map<String, String>? = null,
    val population: Double,
)

data class Name(
    val common: String,
    val official: String
)