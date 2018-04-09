package com.hdd.kotlinapi.infastructures.module

import android.app.Application
import com.hdd.kotlin_caf.scope.ApplicationScope
import com.hdd.kotlin_caf.services.authentication.AuthenticationManagerConfiguration
import com.hdd.kotlin_caf.services.network.DefaultNetworkProvider
import com.hdd.kotlin_caf.services.network.NetworkProvider
import com.hdd.kotlinapi.ApiURL
import com.hdd.kotlinapi.services.authentication.AuthenticationService
import com.hdd.kotlinapi.services.authentication.DefaultAuthenticationService
import com.hdd.kotlinapi.services.authentication.RestAuthenticationService
import com.hdd.kotlinapi.services.province.DefaultProvinceService
import com.hdd.kotlinapi.services.province.ProvinceService
import com.hdd.kotlinapi.services.province.RestProvinceService
import com.hdd.kotlinapi.utils.Constants
import dagger.Module
import dagger.Provides

/**
 * Created on 2/28/2018.
 * @author duonghd
 */

@Module
class ApplicationModule(val app: Application) {
/*    @Provides
    @ApplicationScope
    fun provideContext(): Context {
        return app
    }

    @Provides
    @ApplicationScope
    fun provideApplication(): Application {
        return app
    }*/

    @Provides
    @ApplicationScope
    fun provideNetworkProvider(): NetworkProvider {
        return DefaultNetworkProvider(app, ApiURL.ROOT_URL)
    }

    @Provides
    @ApplicationScope
    fun provideAuthenticationService(networkProvider: NetworkProvider): AuthenticationService {
        val restAuthenticationService = networkProvider.provideApi(RestAuthenticationService::class.java)
        return DefaultAuthenticationService(networkProvider, restAuthenticationService,
                AuthenticationManagerConfiguration(Constants.KEY_USER_AUTH, true))
    }

    @Provides
    @ApplicationScope
    fun provideProvinceService(networkProvider: NetworkProvider): ProvinceService {
        val restProvinceService = networkProvider.provideApi(RestProvinceService::class.java)
        return DefaultProvinceService(networkProvider, restProvinceService)
    }
}