package com.salt.apps.core.di.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.salt.apps.core.data.source.local.datastore.PreferencesDataStore
import com.salt.apps.core.data.source.local.datastore.UserPreferencesSerializer
import com.salt.apps.core.datastore.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideUserPreferencesDataStore(
        @ApplicationContext context: Context,
        userPreferencesSerializer: UserPreferencesSerializer
    ): DataStore<UserPreferences> =
        DataStoreFactory.create(
            serializer = userPreferencesSerializer,
            produceFile = { context.dataStoreFile("user_preferences.pb") },
            scope = CoroutineScope(Dispatchers.IO)
        )

    @Provides
    @Singleton
    fun providePreferencesDataStore(
        userPreferences: DataStore<UserPreferences>
    ): PreferencesDataStore {
        return PreferencesDataStore(userPreferences)
    }
}