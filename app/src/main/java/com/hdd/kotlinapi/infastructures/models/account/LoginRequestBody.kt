package com.hdd.kotlinapi.infastructures.models.account

import com.hdd.kotlin_caf.services.authentication.model.LoginRequest

/**
 * Created on 3/1/2018.
 * @author duonghd
 */

class LoginRequestBody(private var username: String, private var password: String) : LoginRequest {
    override fun getEmail(): String {
        TODO("not implemented")
    }

    override fun setEmail(email: String) {
        TODO("not implemented")
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun setUsername(username: String) {
        this.username = username
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun setPassword(password: String) {
        this.password = password
    }
}