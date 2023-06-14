package com.ashehata.brightskies_task.modules.recipe_details.presentation.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.ashehata.brightskies_task.modules.recipes.presentation.model.RecipeUIModel

@Composable
fun RecipeDetailsScreenContent(
    recipe: MutableState<RecipeUIModel>,
    onAddRecipeToFavourite: (RecipeUIModel) -> Unit,
    onRemoveRecipeFromFavourite: (RecipeUIModel) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 4.dp,
                title = {
                    Text(
                        text = recipe.value.name ?: "",
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                backgroundColor = Color.White,
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackPressed()
                        },
                    ) {
                        Icon(Icons.Filled.ArrowBack, null, tint = Color.Black)
                    }
                },
                actions = {
                    val colorAnimated = animateColorAsState(
                        targetValue = if (recipe.value.isFavourite) Color.Red else Color.Black
                    )

                    IconButton(onClick = {
                        if (recipe.value.isFavourite)
                            onRemoveRecipeFromFavourite(recipe.value)
                        else
                            onAddRecipeToFavourite(recipe.value)
                        recipe.value =
                            recipe.value.copy(isFavourite = recipe.value.isFavourite.not())
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = null,
                            tint = colorAnimated.value
                        )
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
                    Modifier
                        .fillMaxSize()
                        .background(Color.White),
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
                                .data(recipe.value.image)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                        )

                    }

                    item {
                        RecipeDetailsItem(
                            title = R.string.description,
                            description = recipe.value.description ?: ""
                        )
                    }

                    item {
                        RecipeDetailsItem(
                            title = R.string.carbos,
                            description = recipe.value.carbos ?: ""
                        )
                    }

                    item {
                        RecipeDetailsItem(
                            title = R.string.calories,
                            description = recipe.value.calories ?: ""
                        )
                    }

                    item {
                        RecipeDetailsItem(
                            title = R.string.proteins,
                            description = recipe.value.proteins ?: ""
                        )
                    }

                    item {
                        RecipeDetailsItem(
                            title = R.string.fats,
                            description = recipe.value.fats ?: ""
                        )
                    }

                    item {
                        RecipeDetailsItem(
                            title = R.string.ingredients,
                            description = recipe.value.ingredients?.joinToString(separator = ", \n")
                                ?: ""
                        )
                    }


                }
            }
        }
    )
}