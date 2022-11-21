package com.vanced.store.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val Context.datastore by preferencesDataStore("preferences")

val preferenceModule = module {
    single {
        androidContext().datastore
    }
}