package com.example.paisestestemobile.utils

import java.text.DecimalFormat

fun formatArea(area: Double?): String {
    val decimalFormat = DecimalFormat("#,##0.0")
    return decimalFormat.format(area)
}

fun formatPopulation(area: Double?): String {
    val decimalFormat = DecimalFormat("#,###")
    return decimalFormat.format(area)
}