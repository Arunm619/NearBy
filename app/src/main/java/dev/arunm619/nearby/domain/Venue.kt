package dev.arunm619.nearby.domain

data class Venue(
    val id: Int,
    val name: String,
    val address: String,
    val url: String,
    val imageUrl: String? = null
)