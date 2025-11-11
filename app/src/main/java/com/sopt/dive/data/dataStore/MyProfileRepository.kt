package com.sopt.dive.data.dataStore

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.sopt.dive.data.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MyProfileRepository(private val context: Context){
    private val dataStore = context.dataStore
    private val keys = DataStoreKey

    val isSignedInFlow: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[keys.IS_SIGNED_IN] ?: false
    }

    val myProfileFlow: Flow<User> = dataStore.data.map { prefs ->
        val id = prefs[keys.USER_ID] ?: ""
        val pw = prefs[keys.USER_PW] ?: ""
        val nickname = prefs[keys.USER_NICKNAME] ?: ""
        val drinking = prefs[keys.USER_DRINKING] ?: ""
        val name = prefs[keys.USER_NAME] ?: ""

        User(id, pw, nickname, drinking, name)
    }

    val userIdFlow: Flow<String> = dataStore.data.map { prefs ->
        prefs[keys.USER_ID] ?: ""
    }

    suspend fun setSignInStatus(isSignedIn: Boolean){
        dataStore.edit { prefs ->
            prefs[keys.IS_SIGNED_IN] = isSignedIn
        }
    }

    suspend fun saveMyProfile(user: User) {
        dataStore.edit { prefs ->
            prefs[keys.USER_ID] = user.id
            prefs[keys.USER_PW] = user.pw
            prefs[keys.USER_NICKNAME] = user.nickname
            prefs[keys.USER_DRINKING] = user.drinking
            prefs[keys.USER_NAME] = user.name
        }
    }
}
