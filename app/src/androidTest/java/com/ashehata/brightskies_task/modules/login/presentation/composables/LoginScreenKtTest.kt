package com.ashehata.brightskies_task.modules.login.presentation.composables

import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.ashehata.brightskies_task.R
import com.ashehata.brightskies_task.modules.home.HomeActivity
import com.ashehata.brightskies_task.ui.theme.AppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class LoginScreenKtTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<HomeActivity>()

    private val emailInputField by lazy {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.enter_email))
    }

    private val emailErrorMessageTextField by lazy {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.invalid_email))
    }

    private val passwordInputField by lazy {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.enter_password))
    }

    private val loginButton by lazy {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.login))
    }

    private val loadingView by lazy {
        composeTestRule.onNodeWithTag("loading_view")
    }

    @Before
    fun setup() {
        composeTestRule.activity.setContent {
            AppTheme {
                LoginScreen()
            }
        }
    }

    @Test
    fun insert_valid_email_and_password_then_login_button_is_enabled() {
        emailInputField.assertIsDisplayed()
        passwordInputField.assertIsDisplayed()
        loginButton.assertIsDisplayed()
        loginButton.assertIsNotEnabled()
        emailInputField.performTextInput("example@example.com")
        passwordInputField.performTextInput("123456")
        loginButton.assertIsEnabled()
    }

    @Test
    fun insert_invalid_email_and_password_then_login_button_is_not_enabled() {
        emailInputField.assertIsDisplayed()
        passwordInputField.assertIsDisplayed()
        loginButton.assertIsDisplayed()
        loginButton.assertIsNotEnabled()
        emailInputField.performTextInput("@example.com")
        passwordInputField.performTextInput("123456")
        emailErrorMessageTextField.assertIsDisplayed()
        loginButton.assertIsNotEnabled()
    }

    @Test
    fun insert_invalid_email_and_empty_password_then_login_button_is_not_enabled() {
        emailInputField.assertIsDisplayed()
        passwordInputField.assertIsDisplayed()
        loginButton.assertIsDisplayed()
        loginButton.assertIsNotEnabled()
        emailInputField.performTextInput("@example.com")
        passwordInputField.performTextInput("")
        emailErrorMessageTextField.assertIsDisplayed()
        loginButton.assertIsNotEnabled()
    }

    @Test
    fun insert_valid_email_and_valid_password_then_press_login_loading_is_displayed() {
        emailInputField.performTextInput("example@example.com")
        passwordInputField.performTextInput("123456")
        loginButton.assertIsEnabled()
        loginButton.performClick()
        loadingView.assertIsDisplayed()
    }


}