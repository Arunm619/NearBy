package dev.arunm619.nearby.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import dev.arunm619.nearby.data.local.VenueDatabase
import dev.arunm619.nearby.data.local.VenueEntity
import dev.arunm619.nearby.data.mappers.toVenueEntity
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class VenueRemoteMediator(
    private val venueDb: VenueDatabase,
    private val venueAPI: VenueAPI,
) : RemoteMediator<Int, VenueEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, VenueEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val dataSize = state.pages.last().data.size
                    (dataSize / state.config.pageSize) + 1
                }
            }

            Timber.d("Arun, the loadKey is $loadKey")
            val venueBackendResponse = venueAPI.getVenuesV2(
                page = loadKey,
                pageCount = state.config.pageSize,
            )

            venueDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    venueDb.dao.clearAll()
                }
                val venueEntities = venueBackendResponse.venues.map { it.toVenueEntity() }
                Timber.d("Arun, venueBackendResponse is ${venueBackendResponse.venues.size}")
                venueDb.dao.upsertAll(venueEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = venueBackendResponse.venues.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}