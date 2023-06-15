package com.ashehata.brightskies_task.common.presentation

import com.ashehata.brightskies_task.common.presentation.Validation.validateEmail
import org.junit.Assert
import org.junit.Test

class ValidationTest {


    private val validEmails = listOf(
        "ahmed@co.com",
        "ahmed_123@mo.com",
        "ahmed@tri.com",
        "ahmed_ali_123@listOf.com",
        "ahmedahmedahmedahmedahmed@domain.net",
    )

    private val inValidEmails = listOf(
        "#@%^%#@#@#.com",
        "@example.com",
        "Joe Smith <email@example.com>",
        "email@example@example.com",
    )

    @Test
    fun `validateEmail() with valid emails returns ValidationMessageType_Valid`() {
        validEmails.forEach {
            Assert.assertEquals(ValidationMessageType.Valid, it.validateEmail())
        }
    }


    @Test
    fun `validateEmail() with inValid emails returns ValidationMessageType_Invalid`() {
        inValidEmails.forEach {
            Assert.assertEquals(
                ValidationMessageType.Invalid(ValidationType.Email),
                it.validateEmail()
            )
        }
    }


}