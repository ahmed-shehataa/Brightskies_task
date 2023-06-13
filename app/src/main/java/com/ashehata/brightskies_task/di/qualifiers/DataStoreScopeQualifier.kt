package com.ashehata.brightskies_task.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class DataStoreScopeQualifier(
    /** The name.  */
    val value: String = ""
)

