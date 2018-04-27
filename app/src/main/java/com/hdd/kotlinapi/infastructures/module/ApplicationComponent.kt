package com.hdd.kotlinapi.infastructures.module

import com.hdd.kotlin_caf.scope.ApplicationScope
import com.hdd.kotlin_caf.services.network.NetworkProvider
import com.hdd.kotlinapi.services.authentication.AuthenticationService
import com.hdd.kotlinapi.services.gcm.GcmService
import com.hdd.kotlinapi.services.province.ProvinceService
import dagger.Component
import org.greenrobot.eventbus.EventBus

/**
 * Created on 2/28/2018.
 * @author duonghd
 */

@ApplicationScope
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
    fun evenbusProvide(): EventBus

    fun networkProvide(): NetworkProvider

    fun gcmService(): GcmService

    fun authenticationService(): AuthenticationService

    fun provinceService(): ProvinceService
}