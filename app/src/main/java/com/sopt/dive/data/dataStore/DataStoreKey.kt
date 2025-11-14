package com.sopt.dive.data.dataStore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKey {
    val IS_SIGNED_IN = booleanPreferencesKey("is_signed_in")
    val USER_ID = stringPreferencesKey("user_id")
    val USER_PW = stringPreferencesKey("user_pw")
    val USER_NICKNAME = stringPreferencesKey("user_nickname")
    val USER_DRINKING = stringPreferencesKey("user_drinking")
    val USER_NAME = stringPreferencesKey("user_name")
}
