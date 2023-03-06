package com.sngular.rgarciavital.enums

enum class ApiType(
    val url: String
) {
    ENDPOINT("https://api.themoviedb.org/3/"),
    FIREBASE("https://maps.googleapis.com/")
}