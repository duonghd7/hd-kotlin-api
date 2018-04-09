package com.hdd.kotlinapi

import android.annotation.SuppressLint
import android.app.Application
import com.hdd.kotlinapi.infastructures.module.ApplicationComponent
import com.hdd.kotlinapi.infastructures.module.ApplicationModule
import com.hdd.kotlinapi.infastructures.module.DaggerApplicationComponent
import com.orhanobut.hawk.Hawk
import com.orhanobut.hawk.HawkBuilder
import org.androidannotations.annotations.EApplication

/**
 * Created on 2/28/2018.
 * @author duonghd
 */

@SuppressLint("Registered")
@EApplication
open class MainApplication : Application() {
    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSharedPrefStorage(this)).build()

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    open fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }
}