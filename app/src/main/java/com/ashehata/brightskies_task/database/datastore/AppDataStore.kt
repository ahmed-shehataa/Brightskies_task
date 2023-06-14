package com.ashehata.brightskies_task.database.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ashehata.brightskies_task.modules.user.domain.model.UserDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppDataStore @Inject constructor(private val context: Context, scope: CoroutineScope) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "MY_PREF")
    private val isLoggedInPref = booleanPreferencesKey("is_logged_in")
    private val userPref = stringPreferencesKey("user_pref")

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

    suspend fun setUser(userDomainModel: UserDomainModel) {
        context.dataStore.edit { settings ->
            settings[userPref] = userDomainModel.toString()
        }
        setIsLoggedIn(true)
    }

    suspend fun getUser(): UserDomainModel {
        val userString = context.dataStore.data.first()[userPref]
        return UserDomainModel.create(userString ?: "")
    }

    suspend fun clearUser() {
        isUserLoggedIn = false
        context.dataStore.edit {settings ->
            settings[userPref] = ""
            settings[isLoggedInPref] = false
        }
    }
}