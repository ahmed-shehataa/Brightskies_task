package com.ashehata.brightskies_task.common.domain.validation


import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.ashehata.brightskies_task.R

enum class ValidationType {
    Text, Email, Password
}

data class InputWrapper(
    var inputValue: MutableState<String> = mutableStateOf(""),
    var borderColor: Color = Color.Gray,
    val validationType: ValidationType? = ValidationType.Text
) {

    var validationMessageResId: Int = R.string.empty_lbl

    fun onValueChange(input: String) {
        inputValue.value = input
        validationMessageResId = when (validationType) {
            ValidationType.Email -> input.validateEmail()
            else -> input.validateText()
        }
        borderColor = if (isValid) {
            Color.Gray
        } else {
            Color.Red
        }
    }

    val isValid: Boolean
        get() = validationMessageResId == R.string.empty_lbl && inputValue.value.isNotEmpty()

}

private fun String.validateEmail(): Int {
    return if (this.isEmpty()) R.string.empty_field
    else if (Patterns.EMAIL_ADDRESS.matcher(this.trim()).matches().not()) R.string.invalid_email
    else R.string.empty_lbl
}

private fun String.validateText(): Int {
    return if (this.isEmpty()) R.string.empty_field
    else R.string.empty_lbl
}