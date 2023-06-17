package com.ashehata.brightskies_task.modules.user.presentaion.mapper

import com.ashehata.brightskies_task.modules.user.domain.model.UserDomainModel
import com.ashehata.brightskies_task.modules.user.presentaion.model.UserUIModel


fun UserDomainModel.toUIModel() = UserUIModel(
    userName = name ?: "",
    email = email,
)