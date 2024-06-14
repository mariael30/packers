package org.d3if3137.packers.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreRegister(private val context: Context) {
    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_prefs")
        val USERNAME_KEY = stringPreferencesKey("username")
        val PASSWORD_KEY = stringPreferencesKey("password")
    }

    val getUsername: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USERNAME_KEY] ?: ""
        }

    suspend fun saveUsername(name:String){
        context.dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = name
        }
    }
    val getPassword: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[PASSWORD_KEY] ?: ""
        }

    suspend fun savePassword(name:String){
        context.dataStore.edit { preferences ->
            preferences[PASSWORD_KEY] = name
        }
    }
}