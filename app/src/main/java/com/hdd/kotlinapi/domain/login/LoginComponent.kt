package com.hdd.kotlinapi.domain.login

import com.hdd.kotlin_caf.scope.ActivityScope
import com.hdd.kotlinapi.infastructures.module.ActivityModule
import com.hdd.kotlinapi.infastructures.module.ApplicationComponent
import dagger.Component

/**
 * Created on 4/26/2018.
 * @author duonghd
 */

@ActivityScope
@Component(dependencies = [(ApplicationComponent::class)],
        modules = [(ActivityModule::class)])
interface LoginComponent {
    fun inject(loginActivity: LoginActivity)
}