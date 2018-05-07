package com.hdd.kotlinapi

import android.annotation.SuppressLint
import android.support.multidex.MultiDexApplication
import android.util.Log
import com.hdd.kotlinapi.infastructures.module.ApplicationComponent
import com.hdd.kotlinapi.infastructures.module.ApplicationModule
import com.hdd.kotlinapi.infastructures.module.DaggerApplicationComponent
import com.hdd.kotlinapi.utils.Constants.UNIQUE_KEY_DEVICE_ID
import com.onesignal.OneSignal
import com.orhanobut.hawk.Hawk
import com.orhanobut.hawk.HawkBuilder
import org.androidannotations.annotations.EApplication

/**
 * Created on 2/28/2018.
 * @author duonghd
 */

@SuppressLint("Registered")
@EApplication
open class MainApplication : MultiDexApplication() {
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

        OneSignal.startInit(this)
                //.setNotificationReceivedHandler(new MyGcmPushReceiver(this))
                //.setNotificationOpenedHandler(MyGcmOpenedHandler(this))
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init()

        OneSignal.idsAvailable({ userId, registrationId ->
            if (registrationId != null && Hawk.isBuilt()) {
                Hawk.put(UNIQUE_KEY_DEVICE_ID, registrationId)
                Log.i("gcm", "userId: $userId")
                Log.i("gcm", "registrationId: $registrationId")
            }
        })
    }

    open fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }
}