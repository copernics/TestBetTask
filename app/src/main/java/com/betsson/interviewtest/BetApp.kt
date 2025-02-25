package com.betsson.interviewtest

import android.app.Application
import com.betsson.interviewtest.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BetApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BetApp)
            modules(appModule)
        }
    }
}
