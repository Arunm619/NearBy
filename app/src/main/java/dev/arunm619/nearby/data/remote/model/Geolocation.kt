package dev.arunm619.nearby.data.remote.model

data class Geolocation(
    val city: String,
    val country: String,
    val display_name: String,
    val lat: Double,
    val lon: Double,
    val metro_code: Any,
    val postal_code: String,
    val range: String,
    val state: String
)