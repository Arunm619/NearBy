package dev.arunm619.nearby.data.remote.model

data class Meta(
    val geolocation: Geolocation,
    val page: Int,
    val per_page: Int,
    val took: Int,
    val total: Int
)