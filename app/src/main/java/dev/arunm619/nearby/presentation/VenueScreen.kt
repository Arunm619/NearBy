package dev.arunm619.nearby.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import dev.arunm619.nearby.domain.Venue

@Composable
fun VenueScreen(
    venues: LazyPagingItems<Venue>
) {

    var rangeInKmsPosition by remember { mutableStateOf(12f) }

    val context = LocalContext.current
    LaunchedEffect(key1 = venues.loadState) {
        if (venues.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (venues.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    val uriHandler = LocalUriHandler.current

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Slider(
                value = rangeInKmsPosition,
                onValueChange = { rangeInKmsPosition = it },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.secondary,
                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                    inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                steps = 1,
                valueRange = 1f..100f
            )
            Text(text = "Range: ${rangeInKmsPosition.toInt()} Kms")
            Box {
                if (venues.loadState.refresh is LoadState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    Column {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(venues) { venue ->
                                if (venue != null) {
                                    VenueItem(
                                        venue = venue,
                                        modifier = Modifier.fillMaxWidth(),
                                        onClick = {
                                            uriHandler.openUri(venue.url)
                                        }
                                    )
                                }
                            }
                            item {
                                if (venues.loadState.append is LoadState.Loading) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }

                }

            }
        }

    }
}