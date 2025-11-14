package com.sopt.dive.data.dataStore

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.sopt.dive.data.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MyProfileRepository(private val context: Context) {
    private val dataStore = context.dataStore
    private val keys = DataStoreKey

    fun getIsSignedIn(): Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[keys.IS_SIGNED_IN] ?: false
    }

    fun getUserId(): Flow<Int> = dataStore.data.map { prefs ->
        prefs[keys.USER_ID] ?: 0
    }

    fun getMyProfile(): Flow<User> = dataStore.data.map { prefs ->
        val name = prefs[keys.USER_NAME] ?: ""
        val pw = prefs[keys.USER_PW] ?: ""
        val nickname = prefs[keys.USER_NICKNAME] ?: ""
        val email = prefs[keys.USER_EMAIL] ?: ""
        val age = prefs[keys.USER_AGE] ?: 0

        User(name, pw, nickname, email, age)
    }

    suspend fun setSignInStatus(isSignedIn: Boolean) {
        dataStore.edit { prefs ->
            prefs[keys.IS_SIGNED_IN] = isSignedIn
        }
    }

    suspend fun saveMyId(userId: Int) {
        dataStore.edit { prefs ->
            prefs[keys.USER_ID] = userId
        }
    }

    suspend fun saveMyProfile(user: User) {
        dataStore.edit { prefs ->
            prefs[keys.USER_NAME] = user.name
            prefs[keys.USER_PW] = user.pw
            prefs[keys.USER_NICKNAME] = user.nickname
            prefs[keys.USER_EMAIL] = user.email
            prefs[keys.USER_AGE] = user.age
        }
    }
}
