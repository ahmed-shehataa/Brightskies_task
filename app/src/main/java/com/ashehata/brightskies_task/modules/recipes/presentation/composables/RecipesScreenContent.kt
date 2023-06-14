package com.ashehata.brightskies_task.modules.recipes.presentation.composables

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ashehata.brightskies_task.R
import com.ashehata.brightskies_task.common.presentation.compose.LoadingView
import com.ashehata.brightskies_task.common.presentation.compose.NetworkErrorView
import com.ashehata.brightskies_task.modules.recipes.presentation.model.RecipeUIModel
import com.ashehata.brightskies_task.modules.recipes.presentation.model.RecipesScreenMode


@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun RecipesScreenContent(
    allRecipes: List<RecipeUIModel?>,
    favRecipes: List<RecipeUIModel?>,
    isLoading: Boolean,
    isRefreshing: Boolean,
    isNetworkError: Boolean,
    onRecipeClicked: (RecipeUIModel) -> Unit,
    onRefresh: () -> Unit,
    onAddRecipeToFavourite: (RecipeUIModel) -> Unit,
    onChangeScreenMode: () -> Unit,
    screenMode: RecipesScreenMode,
    onClearAllRecipeFromFavourite: () -> Unit,
    onRemoveRecipeFromFavourite: (RecipeUIModel) -> Unit,
    onBackPressed: () -> Unit,
    onLogout: () -> Unit,
) {

    val refreshState = rememberPullRefreshState(isRefreshing, onRefresh)
    val allListState = rememberLazyListState()
    val favListState = rememberLazyListState()


    Column(Modifier.pullRefresh(refreshState)) {
        val backIconNavigation: (@Composable () -> Unit)? = remember(screenMode) {

            if (screenMode == RecipesScreenMode.FavouriteOnly) {
                {
                    IconButton(
                        onClick = {
                            onBackPressed()
                        },
                    ) {
                        Icon(Icons.Filled.ArrowBack, null, tint = Color.Black)
                    }
                }
            } else null

        }

        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(stringResource(id = R.string.recipes))
            },
            backgroundColor = Color.White,
            navigationIcon = backIconNavigation,
            actions = {

                val colorAnimated = animateColorAsState(
                    targetValue = if (screenMode == RecipesScreenMode.FavouriteOnly) Color.Red else Color.Black
                )

                AnimatedVisibility(
                    visible = screenMode == RecipesScreenMode.FavouriteOnly && favRecipes.isNotEmpty(),
                    enter = scaleIn(),
                    exit = scaleOut()
                ) {
                    IconButton(onClick = {
                        onClearAllRecipeFromFavourite()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = null,
                        )
                    }
                }


                IconButton(onClick = {
                    onChangeScreenMode()

                }) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        tint = colorAnimated.value
                    )
                }

                IconButton(onClick = {
                    onLogout()

                }) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                    )
                }


            })

        if (isNetworkError) {
            NetworkErrorView(onRefresh)

        } else if (isLoading) {
            LoadingView()

        } else {

            Box(Modifier.pullRefresh(refreshState)) {

                if (screenMode == RecipesScreenMode.All) {
                    AllRecipesList(
                        listState = allListState,
                        allRecipes = allRecipes,
                        onRecipeClicked = onRecipeClicked,
                        onAddRecipeToFavourite = onAddRecipeToFavourite,
                        onRemoveRecipeFromFavourite = onRemoveRecipeFromFavourite
                    )

                } else {

                    FavRecipesList(
                        listState = favListState,
                        allRecipes = favRecipes,
                        onRecipeClicked = onRecipeClicked,
                        onAddRecipeToFavourite = onAddRecipeToFavourite,
                        onRemoveRecipeFromFavourite = onRemoveRecipeFromFavourite
                    )
                }

                PullRefreshIndicator(
                    isRefreshing,
                    refreshState,
                    Modifier.align(Alignment.TopCenter)
                )

            }

        }

    }
}