package dev.arunm619.nearby.remote

data class VenueX(
    val access_method: Any,
    val address: String,
    val capacity: Int,
    val city: String,
    val country: String,
    val display_location: String,
    val extended_address: String,
    val has_upcoming_events: Boolean,
    val id: Int,
    val links: List<Any>,
    val location: Location,
    val metro_code: Int,
    val name: String,
    val name_v2: String,
    val num_upcoming_events: Int,
    val popularity: Int,
    val postal_code: String,
    val score: Int,
    val slug: String,
    val state: Any,
    val stats: Stats,
    val timezone: String,
    val url: String
)