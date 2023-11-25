package dev.arunm619.nearby.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface VenueDao {

    @Upsert
    suspend fun upsertAll(venues: List<VenueEntity>)

    @Query("SELECT * FROM VenueEntity")
    fun pagingSource(): PagingSource<Int, VenueEntity>

    @Query("DELETE FROM VenueEntity")
    suspend fun clearAll()
}