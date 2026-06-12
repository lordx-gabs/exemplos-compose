package com.example.testecompose.data.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.testecompose.data.repository.ThemeRepositoryImpl
import com.example.testecompose.domain.repository.ThemeRepository
import com.example.testecompose.ui.ThemeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    viewModel { ThemeViewModel(get()) }
}

val storageModule = module {
    singleOf(::ThemeRepositoryImpl) bind ThemeRepository::class
    single {
        PreferenceDataStoreFactory.create {
            androidContext().preferencesDataStoreFile("preferences")
        }
    }
}
