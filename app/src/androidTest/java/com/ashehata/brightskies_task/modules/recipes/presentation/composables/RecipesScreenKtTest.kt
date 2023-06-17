package com.ashehata.brightskies_task.modules.recipes.presentation.composables

import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.ashehata.brightskies_task.R
import com.ashehata.brightskies_task.database.datastore.AppDataStore
import com.ashehata.brightskies_task.database.room.AppDatabase
import com.ashehata.brightskies_task.modules.home.HomeActivity
import com.ashehata.brightskies_task.modules.recipes.presentation.viewmodel.RecipesViewModel
import com.ashehata.brightskies_task.ui.theme.AppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep
import javax.inject.Inject


@HiltAndroidTest
class RecipesScreenKtTest {


    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<HomeActivity>()

    @Inject
    lateinit var appDatabase: AppDatabase

    private lateinit var recipesViewModel: RecipesViewModel

    private val backButton by lazy {
        composeTestRule.onNodeWithContentDescription("ArrowBack")
    }

    private val favButton by lazy {
        composeTestRule.onNodeWithContentDescription("Favorite")
    }

    private val logoutButton by lazy {
        composeTestRule.onNodeWithContentDescription("Logout")
    }

    private val deleteButton by lazy {
        composeTestRule.onNodeWithContentDescription("DeleteALL")
    }

    private val retryButton by lazy {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.retry))
    }

    private val deleteAllDialogButton by lazy {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.delete))
    }

    private val loadingView by lazy {
        hasTestTag("loading_view")
    }

    private val networkErrorView by lazy {
        hasTestTag("NetworkErrorView")
    }

    private val device by lazy {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }


    @Before
    fun setup() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            AppTheme {
                recipesViewModel = hiltViewModel()
                RecipesScreen(recipesViewModel)
            }
        }
    }

    @After
    fun tearDown() {
        runBlocking {
            appDatabase.recipesDao().clearAll()
        }
    }

    @Test
    fun initial_screen_icons_displayed() {
        backButton.assertDoesNotExist()
        favButton.assertIsDisplayed()
        logoutButton.assertIsDisplayed()
    }


    @Test
    fun press_fav_then_back_button_appears() {
        backButton.assertDoesNotExist()
        favButton.assertIsDisplayed()
        logoutButton.assertIsDisplayed()
        favButton.performClick()
        backButton.assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun add_recipe_to_fav_then_check_is_added_to_fav_recipes() {
        backButton.assertDoesNotExist()
        favButton.assertIsDisplayed()
        logoutButton.assertIsDisplayed()

        composeTestRule.waitUntilDoesNotExist(loadingView)

        val allList = device.findObject(By.res("allList"))
        if ((allList.childCount != 0)) {
            allList.children[0].findObject(By.descContains("Favorite_recipe")).click()
        }

        favButton.performClick()

        composeTestRule.waitUntilDoesNotExist(hasTestTag("allList"))

        val favList = device.findObject(By.res("favList"))

        if ((favList.childCount > 0)) {
            val hasEmptyView = favList.findObject(By.res("EmptyListPlaceholder"))
            if (hasEmptyView != null) {
                deleteButton.assertDoesNotExist()
            } else {
                deleteButton.assertIsDisplayed()
                favList.children[0].click()

            }
        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun add_all_recipes_to_fav_then_delete_them() {
        backButton.assertDoesNotExist()
        favButton.assertIsDisplayed()
        logoutButton.assertIsDisplayed()

        composeTestRule.waitUntilDoesNotExist(loadingView)

        val allList = device.findObject(By.res("allList"))
        if ((allList.childCount != 0)) {
            allList.children.forEach {
                it.findObject(By.descContains("Favorite_recipe")).click()
            }
        }

        favButton.performClick()

        composeTestRule.waitUntilDoesNotExist(hasTestTag("allList"))

        val favList = device.findObject(By.res("favList"))

        if ((favList.childCount > 0)) {

            deleteButton.performClick()
            deleteAllDialogButton.performClick()
            deleteButton.assertDoesNotExist()

            val hasEmptyView = favList.findObject(By.res("EmptyListPlaceholder"))
            hasEmptyView.click()
        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun reload_data_after_wifi_enabled() {
        device.executeShellCommand("svc wifi disable")
        sleep(1000)

        composeTestRule.waitUntilAtLeastOneExists(networkErrorView)

        device.executeShellCommand("svc wifi enable")
        sleep(5000)

        retryButton.performClick()
        composeTestRule.waitUntilDoesNotExist(loadingView)

        val allList = device.findObject(By.res("allList"))
        if ((allList.childCount != 0)) {

        }
    }


}