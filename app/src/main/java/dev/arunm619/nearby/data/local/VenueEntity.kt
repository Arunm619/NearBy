package dev.arunm619.nearby.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VenueEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val address: String,
    val url: String
)