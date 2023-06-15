package com.ashehata.brightskies_task.common.presentation

import android.util.Patterns
import com.ashehata.brightskies_task.R

object Validation {
    fun String.validateEmail(): Int {
        return if (this.isEmpty()) R.string.empty_field
        else if (Patterns.EMAIL_ADDRESS.matcher(this.trim()).matches().not()) R.string.invalid_email
        else R.string.empty_lbl
    }

    fun String.validateText(): Int {
        return if (this.isEmpty()) R.string.empty_field
        else R.string.empty_lbl
    }
}