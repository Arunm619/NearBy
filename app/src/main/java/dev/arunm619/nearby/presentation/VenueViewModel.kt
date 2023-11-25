package dev.arunm619.nearby.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.arunm619.nearby.data.local.VenueEntity
import dev.arunm619.nearby.data.mappers.toVenue
import dev.arunm619.nearby.domain.Venue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class VenueViewModel @Inject constructor(
    pager: Pager<Int, VenueEntity>
) : ViewModel() {

    val venuePagingFlow: Flow<PagingData<Venue>> = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toVenue() }
        }
        .cachedIn(viewModelScope)
}