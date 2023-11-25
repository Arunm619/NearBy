package dev.arunm619.nearby.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [VenueEntity::class],
    version = 1
)
abstract class VenueDatabase: RoomDatabase() {

    abstract val dao: VenueDao
}