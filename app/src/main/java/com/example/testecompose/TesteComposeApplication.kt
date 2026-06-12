package com.example.testecompose

import android.app.Application
import com.example.testecompose.data.di.appModule
import com.example.testecompose.data.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TesteComposeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TesteComposeApplication)
            modules(
                storageModule,
                appModule
            )
        }
    }
}