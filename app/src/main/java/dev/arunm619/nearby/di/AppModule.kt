package dev.arunm619.nearby.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.arunm619.nearby.data.local.VenueDatabase
import dev.arunm619.nearby.data.local.VenueEntity
import dev.arunm619.nearby.data.remote.VenueAPI
import dev.arunm619.nearby.data.remote.VenueRemoteMediator
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideVenueDatabase(@ApplicationContext context: Context): VenueDatabase {
        return Room.databaseBuilder(
            context,
            VenueDatabase::class.java,
            "venue.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideVenueApi(
        okHttpClient: OkHttpClient
    ): VenueAPI {
        return Retrofit.Builder()
            .baseUrl(VenueAPI.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun provideOKHTTPClient(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context))
            .build()
    }

    @Provides
    @Singleton
    fun provideVenuePager(venueDb: VenueDatabase, venueAPI: VenueAPI): Pager<Int, VenueEntity> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = VenueRemoteMediator(
                venueDb = venueDb,
                venueAPI = venueAPI
            ),
            pagingSourceFactory = {
                venueDb.dao.pagingSource()
            }
        )
    }
}