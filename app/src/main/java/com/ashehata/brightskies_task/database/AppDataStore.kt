package com.ashehata.brightskies_task.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppDataStore @Inject constructor(private val context: Context, scope: CoroutineScope) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "MY_PREF")
    private val isLoggedInPref = booleanPreferencesKey("is_logged_in")

    var isUserLoggedIn: Boolean = false

    init {
        scope.launch {
            isUserLoggedIn = getIsLoggedIn()
        }
    }

    suspend fun setIsLoggedIn(isLoggedIn: Boolean) {
        isUserLoggedIn = isLoggedIn
        context.dataStore.edit { settings ->
            settings[isLoggedInPref] = isLoggedIn
        }
    }

    suspend fun getIsLoggedIn(): Boolean {
        return context.dataStore.data.first()[isLoggedInPref] ?: false
    }
}