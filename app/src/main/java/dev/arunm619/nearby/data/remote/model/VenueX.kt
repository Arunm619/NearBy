package dev.arunm619.nearby.data.remote.model

data class VenueX(
    val access_method: Any? = null,
    val address: String,
    val capacity: Int? = null,
    val city: String? = null,
    val country: String? = null,
    val display_location: String? = null,
    val extended_address: String? = null,
    val has_upcoming_events: Boolean? = null,
    val id: Int,
    val links: List<Any>? = null,
    val location: Location? = null,
    val metro_code: Int? = null,
    val name: String,
    val name_v2: String? = null,
    val num_upcoming_events: Int? = null,
    val popularity: Int? = null,
    val postal_code: String? = null,
    val score: Double? = null,
    val slug: String? = null,
    val state: Any? = null,
    val stats: Stats? = null,
    val timezone: String? = null,
    val url: String
)