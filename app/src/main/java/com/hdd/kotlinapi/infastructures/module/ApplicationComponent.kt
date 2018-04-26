package com.hdd.kotlinapi.infastructures.module

import com.hdd.kotlin_caf.scope.ApplicationScope
import com.hdd.kotlin_caf.services.network.NetworkProvider
import com.hdd.kotlinapi.services.authentication.AuthenticationService
import com.hdd.kotlinapi.services.province.ProvinceService
import dagger.Component

/**
 * Created on 2/28/2018.
 * @author duonghd
 */

@ApplicationScope
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {
    fun networkProvide(): NetworkProvider

    fun authenticationService(): AuthenticationService

    fun provinceService(): ProvinceService
}