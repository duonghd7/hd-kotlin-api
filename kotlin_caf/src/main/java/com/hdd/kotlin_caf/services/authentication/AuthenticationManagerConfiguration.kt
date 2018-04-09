package com.hdd.kotlin_caf.services.authentication

/**
 * Created on 3/2/2018.
 * @author duonghd
 */

class AuthenticationManagerConfiguration(var uniqueStorageKey: String, var useStorage: Boolean) {

    fun getStorageKey(): String {
        return uniqueStorageKey
    }

    fun isUseStorage(): Boolean {
        return useStorage
    }

    fun enableStorage(): AuthenticationManagerConfiguration {
        this.useStorage = true
        return this
    }

    fun disableStorage(): AuthenticationManagerConfiguration {
        this.useStorage = false
        return this
    }
}