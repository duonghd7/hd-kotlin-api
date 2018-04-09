package com.hdd.kotlinapi.domain.retrofit

import com.hdd.kotlin_caf.scope.ActivityScope
import com.hdd.kotlinapi.infastructures.module.ActivityModule
import com.hdd.kotlinapi.infastructures.module.ApplicationComponent
import dagger.Component

/**
 * Created on 3/1/2018.
 * @author duonghd
 */

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class),
        modules = arrayOf(ActivityModule::class))
interface RetrofitComponent {
    fun inject(secondActivity: RetrofitActivity)
}