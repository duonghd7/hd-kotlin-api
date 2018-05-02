package com.hdd.kotlinapi.domain.home

import com.hdd.kotlin_caf.scope.ActivityScope
import com.hdd.kotlinapi.infastructures.module.ActivityModule
import com.hdd.kotlinapi.infastructures.module.ApplicationComponent
import dagger.Component

/**
 * Created on 4/27/2018.
 * @author duonghd
 */

@ActivityScope
@Component(dependencies = [(ApplicationComponent::class)],
        modules = [(ActivityModule::class)])
interface HomeComponent {
    fun inject(homeActivity: HomeActivity)
}
