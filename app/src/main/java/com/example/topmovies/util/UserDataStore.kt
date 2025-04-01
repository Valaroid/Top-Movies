package com.example.topmovies.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataStore @Inject constructor(@ApplicationContext val context: Context) {

    //init DataStore(sakhte data store)
    private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(Constants.USER_DATA_STORE)

    //Init KeyStore(Sakht klid)
    private val keyUserStore = stringPreferencesKey(Constants.USER_KEY_STORE)


    suspend fun saveTokenToDataStore(token: String) {

        context.userDataStore.edit { userDataStore ->

            userDataStore[keyUserStore] = token

        }

    }


    fun getTokenFromDataStore() = context.userDataStore.data.map { userDataStore ->
        userDataStore[keyUserStore] ?: ""

    }


}