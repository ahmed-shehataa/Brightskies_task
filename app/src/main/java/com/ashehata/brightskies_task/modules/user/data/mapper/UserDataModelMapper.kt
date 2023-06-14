package com.ashehata.brightskies_task.modules.user.data.mapper

import com.ashehata.brightskies_task.modules.user.data.model.UserDataModel
import com.ashehata.brightskies_task.modules.user.domain.model.UserDomainModel

fun UserDataModel.toDomainModel(): UserDomainModel =
    UserDomainModel(
        name = this.name,
        email = email,
    )

