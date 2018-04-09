package com.hdd.kotlin_caf.services.authentication

import com.hdd.kotlin_caf.services.authentication.model.LoginRequest
import com.hdd.kotlin_caf.services.authentication.model.LoginResponse
import com.hdd.kotlin_caf.services.authentication.model.LoginSocialRequest
import com.orhanobut.hawk.Hawk

/**
 * Created on 3/2/2018.
 * @author duonghd
 */

abstract class AbstractAuthenticationManager<
        TLoginResponse : LoginResponse,
        TLoginRequest : LoginRequest,
        TLoginSocialRequest : LoginSocialRequest>(val configuration: AuthenticationManagerConfiguration)
    : AuthenticationManager<TLoginResponse, TLoginRequest, TLoginSocialRequest> {

    private var tLoginResponse: TLoginResponse? = null

    override fun isAuthenticated(): Boolean {
        return tLoginResponse != null
    }

    override fun isLoadFromStorageSuccess(): Boolean {
        var isSuccess = false
        if (configuration.isUseStorage() && Hawk.isBuilt()) {
            try {
                val user: TLoginResponse? = Hawk.get(configuration.getStorageKey())
                if (user != null) {
                    this.tLoginResponse = user
                    isSuccess = true
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        return isSuccess
    }

    override fun getLoginResponse(): TLoginResponse? {
        return tLoginResponse
    }

    override fun setLoginResponse(tLoginResponse: TLoginResponse?) {
        this.tLoginResponse = tLoginResponse
        if (configuration.isUseStorage() && Hawk.isBuilt()) {
            if (tLoginResponse != null) {
                Hawk.put(configuration.getStorageKey(), tLoginResponse)
            } else {
                Hawk.remove(configuration.getStorageKey())
            }
        }
    }
}