package com.ashehata.brightskies_task.common.presentation


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.ashehata.brightskies_task.R
import com.ashehata.brightskies_task.common.presentation.Validation.validateEmail
import com.ashehata.brightskies_task.common.presentation.Validation.validateText

enum class ValidationType {
    Text, Email, Password
}

data class InputWrapper(
    var text: MutableState<String> = mutableStateOf(""),
    var isValid: MutableState<Boolean> = mutableStateOf(false),
    var borderColor: Color = Color.Gray,
    val validationType: ValidationType? = ValidationType.Text
) {

    var validationMessageResId: Int = R.string.empty_lbl

    fun onValueChange(input: String) {
        text.value = input
        validationMessageResId = when (validationType) {
            ValidationType.Email -> input.validateEmail()
            else -> input.validateText()
        }
        borderColor = if (isValid.value) {
            Color.Gray
        } else {
            Color.Red
        }
        isValid.value = validationMessageResId == R.string.empty_lbl && text.value.isNotEmpty()
    }
}