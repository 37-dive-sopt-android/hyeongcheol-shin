package com.sopt.dive.data.dataStore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKey {
    val IS_SIGNED_IN = booleanPreferencesKey("is_signed_in")
    val USER_NAME = stringPreferencesKey("user_name")
    val USER_PW = stringPreferencesKey("user_pw")
    val USER_NICKNAME = stringPreferencesKey("user_nickname")
    val USER_EMAIL = stringPreferencesKey("user_email")
    val USER_AGE = intPreferencesKey("user_age")
    val USER_ID = intPreferencesKey("user_id")
}
