package com.gigauri.reptiledb.module.core.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.appDataStore: DataStore<Preferences> by preferencesDataStore(name = PreferencesDataStore.Keys.NAME_APP_PREFERENCES)

class PreferencesDataStore(
    private val context: Context
) {

    private val dataStore = context.appDataStore

    object Keys {
        const val NAME_APP_PREFERENCES = "app_preferences"
        val KEY_LANGUAGE = stringPreferencesKey("language")
        val KEY_OFFLINE_DATA_LAST_TIMESTAMP = longPreferencesKey("offline_data_last_timestamp")
    }

    suspend fun setOfflineDataUpdateTimestamp(timestamp: Long) {
        dataStore.edit {
            it[Keys.KEY_OFFLINE_DATA_LAST_TIMESTAMP] = timestamp
        }
    }

    fun getOfflineDataLastUpdateTimestamp(): Flow<Long> =
        dataStore.data.map { it[Keys.KEY_OFFLINE_DATA_LAST_TIMESTAMP] ?: -1 }


    suspend fun setLanguage(lang: String) {
        dataStore.edit {
            it[Keys.KEY_LANGUAGE] = lang
        }
    }

    fun getLanguage(): Flow<String?> = dataStore.data.map { it[Keys.KEY_LANGUAGE] }
}