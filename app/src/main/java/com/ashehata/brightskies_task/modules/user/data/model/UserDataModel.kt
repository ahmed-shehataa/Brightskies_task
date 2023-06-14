package com.ashehata.brightskies_task.modules.user.data.model

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class UserDataModel(
    @Json(name = "name")
    val name: String = "",
    @Json(name = "email")
    val email: String,
)
