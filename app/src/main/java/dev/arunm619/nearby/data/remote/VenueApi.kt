package dev.arunm619.nearby.data.remote

import dev.arunm619.nearby.data.remote.model.VenueBackendResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface VenueAPI {

    @GET("2/venues")
    suspend fun getVenuesV2(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int,
        @Query("client_id") clientId: String = "Mzg0OTc0Njl8MTcwMDgxMTg5NC44MDk2NjY5",
        @Query("lat") lat: Double? = null,
        @Query("lon") lon: Double? = null,
        @Query("range") radius: String? = "12km",
        @Query("q") query: String? = null
    ): VenueBackendResponse

    companion object {
        const val BASE_URL = "https://api.seatgeek.com/"
    }
}