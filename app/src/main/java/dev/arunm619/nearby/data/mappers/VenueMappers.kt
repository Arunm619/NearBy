package dev.arunm619.nearby.data.mappers

import dev.arunm619.nearby.data.local.VenueEntity
import dev.arunm619.nearby.data.remote.model.VenueX
import dev.arunm619.nearby.domain.Venue

fun VenueX.toVenueEntity(): VenueEntity {
    return VenueEntity(
        id = id,
        name = name,
        address = address,
        url = url
    )
}

fun VenueEntity.toVenue(): Venue {
    return Venue(
        id = id,
        name = name,
        address = address,
        url = url
    )
}