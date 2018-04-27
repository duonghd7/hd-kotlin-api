package com.hdd.kotlinapi.infastructures.module

import android.app.Activity
import android.content.Context
import com.hdd.kotlin_caf.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created on 2/28/2018.
 * @author duonghd
 */

@Module
class ActivityModule(private val activity: Activity) {
    @Provides
    @ActivityScope
    fun provideContext(): Context {
        return activity
    }

    @Provides
    @ActivityScope
    fun provideActivity(): Activity {
        return activity
    }
}