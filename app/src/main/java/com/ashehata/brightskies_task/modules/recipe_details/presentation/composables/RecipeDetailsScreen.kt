package com.ashehata.brightskies_task.modules.recipe_details.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ashehata.brightskies_task.R
import com.ashehata.brightskies_task.common.presentation.compose.ZoomableImage
import com.ashehata.brightskies_task.modules.recipes.presentation.model.RecipeUIModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination
fun RecipeDetailsScreen(
    recipe: RecipeUIModel,
    navigator: DestinationsNavigator
) {

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 4.dp,
                title = {
                    Text(
                        text = recipe.name ?: "",
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                backgroundColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = {
                        navigator.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        }, content = { _ ->

            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                val lazyListState = rememberLazyListState()
                var scrolledY = 0f
                var previousOffset = 0

                LazyColumn(
                    Modifier.fillMaxSize(),
                    lazyListState,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = 12.dp),
                ) {

                    item {
                        AsyncImage(
                            modifier = Modifier
                                .graphicsLayer {
                                    scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                                    translationY = scrolledY * 0.5f
                                    previousOffset = lazyListState.firstVisibleItemScrollOffset
                                }
                                .height(240.dp)
                                .fillMaxWidth(),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(recipe.image)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                        )

                    }

                    item {
                        RecipeDetailsItem(
                            title = R.string.description,
                            description = recipe.description ?: ""
                        )
                    }

                    item {
                        RecipeDetailsItem(
                            title = R.string.carbos,
                            description = recipe.carbos ?: ""
                        )
                    }

                    item {
                        RecipeDetailsItem(
                            title = R.string.calories,
                            description = recipe.calories ?: ""
                        )
                    }

                    item {
                        RecipeDetailsItem(
                            title = R.string.proteins,
                            description = recipe.proteins ?: ""
                        )
                    }

                    item {
                        RecipeDetailsItem(
                            title = R.string.fats,
                            description = recipe.fats ?: ""
                        )
                    }

                    item {
                        RecipeDetailsItem(
                            title = R.string.ingredients,
                            description = recipe.ingredients?.joinToString(separator = ", \n") ?: ""
                        )
                    }


                }
            }
        }
    )

}