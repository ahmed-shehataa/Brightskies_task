package com.ashehata.brightskies_task.modules.home

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.ashehata.brightskies_task.R
import com.ashehata.brightskies_task.modules.user.domain.usecase.LogOutUserUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class HomeActivityTest {

    /**
     * This will test all user flow starting from login, add to fav, delete them then logout
     */

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<HomeActivity>()

    private val emailInputField by lazy {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.enter_email))
    }

    @Inject
    lateinit var logOutUserUseCase: LogOutUserUseCase

    private val passwordInputField by lazy {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.enter_password))
    }

    private val loginButton by lazy {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.login))
    }

    private val loadingView by lazy {
        hasTestTag("loading_view")
    }

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

    private val deleteAllDialogButton by lazy {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.delete))
    }

    private val logoutDialogButton by lazy {
        composeTestRule.onAllNodesWithText(composeTestRule.activity.getString(R.string.logout))
            .onLast()
    }

    private val device by lazy {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        runBlocking {
            logOutUserUseCase.execute()
        }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun test_all_user_flow_starting_from_login_add_to_fav_delete_them_then_logout() {
        emailInputField.assertIsDisplayed()
        passwordInputField.assertIsDisplayed()
        loginButton.assertIsDisplayed()
        loginButton.assertIsNotEnabled()

        emailInputField.performTextInput("ahmed@gmail.com")
        passwordInputField.performTextInput("123456")
        loginButton.assertIsEnabled()
        loginButton.performClick()

        composeTestRule.waitForIdle()
        composeTestRule.waitUntil(5_000) {
            device.hasObject(By.res("loading_view"))
        }

        backButton.assertDoesNotExist()
        favButton.assertIsDisplayed()
        logoutButton.assertIsDisplayed()

        composeTestRule.waitUntilDoesNotExist(loadingView)

        val allList = device.findObject(By.res("allList"))
        if ((allList.childCount != 0)) {
            allList.children[0].findObject(By.descContains("Favorite_recipe")).click()
            allList.children[0].click()
            device.pressBack()
        }

        favButton.performClick()
        composeTestRule.waitUntilDoesNotExist(hasTestTag("allList"))

        deleteButton.performClick()
        deleteAllDialogButton.performClick()
        deleteButton.assertDoesNotExist()

        logoutButton.performClick()
        logoutDialogButton.performClick()


    }

}