package com.hdd.kotlinapi.infastructures.models.account

import com.hdd.kotlin_caf.services.authentication.model.LoginResponse

/**
 * Created on 3/1/2018.
 * @author duonghd
 */

class User(private var id: Int,
           private var username: String,
           private var email: String,
           private var avatar: String,
           private var accessToken: String) : LoginResponse {

    override fun getUsername(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUsername(username: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getEmail(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setEmail(email: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAccessToken(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAccessToken(accessToken: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}