package dev.arunm619.nearby.domain

data class Venue(
    val id: Int,
    val name: String,
    val address: String,
    val url: String,
    val imageUrl: String? = "https://fastly.picsum.photos/id/237/200/300.jpg?hmac=TmmQSbShHz9CdQm0NkEjx1Dyh_Y984R9LpNrpvH2D_U"
)