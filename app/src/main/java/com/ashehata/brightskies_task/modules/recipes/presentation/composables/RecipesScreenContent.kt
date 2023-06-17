package com.ashehata.brightskies_task.modules.recipes.presentation.composables

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ashehata.brightskies_task.R
import com.ashehata.brightskies_task.common.presentation.compose.AlertDialog
import com.ashehata.brightskies_task.common.presentation.compose.LoadingView
import com.ashehata.brightskies_task.common.presentation.compose.NetworkErrorView
import com.ashehata.brightskies_task.modules.recipes.presentation.model.RecipeUIModel
import com.ashehata.brightskies_task.modules.recipes.presentation.model.RecipesScreenMode
import com.ashehata.brightskies_task.modules.user.presentaion.model.UserUIModel


@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class,
    ExperimentalComposeUiApi::class
)
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
    logoutDialogState: MutableState<Boolean>,
    deleteAllRecipesDialogState: MutableState<Boolean>,
    user: UserUIModel?,
) {

    val refreshState = rememberPullRefreshState(isRefreshing, onRefresh)
    val allListState = rememberLazyListState()
    val favListState = rememberLazyListState()

    val screenTitle: @Composable () -> String = remember(screenMode) {
        {
            when (screenMode) {
                RecipesScreenMode.All -> String.format(
                    stringResource(id = R.string.hi_user_name),
                    user?.userName ?: ""
                )
                RecipesScreenMode.FavouriteOnly -> stringResource(id = R.string.fav_recipes)
            }
        }
    }


    Column(Modifier.pullRefresh(refreshState).semantics {
        testTagsAsResourceId = true
    }) {
        val backIconNavigation: (@Composable () -> Unit)? = remember(screenMode) {

            if (screenMode == RecipesScreenMode.FavouriteOnly) {
                {
                    IconButton(
                        onClick = {
                            onBackPressed()
                        },
                    ) {
                        Icon(Icons.Filled.ArrowBack, "ArrowBack", tint = Color.Black)
                    }
                }
            } else null

        }

        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(screenTitle(), maxLines = 1, overflow = TextOverflow.Ellipsis)
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
                        deleteAllRecipesDialogState.value = true
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "DeleteALL",
                        )
                    }
                }


                IconButton(onClick = {
                    onChangeScreenMode()

                }) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorite",
                        tint = colorAnimated.value
                    )
                }

                IconButton(onClick = {
                    logoutDialogState.value = true
                }) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_logout),
                        contentDescription = "Logout",
                        tint = Color.Black,
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

        AlertDialog(
            state = logoutDialogState,
            title = R.string.logout,
            content = R.string.are_you_sure_to_logout,
            positiveTitleRes = R.string.logout,
            negativeTitleRes = R.string.cancel,
            positive = {
                onLogout()
            }
        )

        AlertDialog(
            state = deleteAllRecipesDialogState,
            title = R.string.delete_all_fav,
            content = R.string.are_you_sure_to_delete_all_favourite,
            positiveTitleRes = R.string.delete,
            negativeTitleRes = R.string.cancel,
            positive = {
                onClearAllRecipeFromFavourite()
            }
        )

    }
}