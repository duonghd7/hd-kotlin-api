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
           private var accessToken: String,
           private var created_at: String,
           private var updated_at: String) : LoginResponse {

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getAvatar(): String {
        return avatar
    }

    fun setAvatar(avatar: String) {
        this.avatar = avatar
    }

    fun getCreatedAt(): String {
        return created_at
    }

    fun setCreatedAt(created_at: String) {
        this.created_at = created_at
    }

    fun getUpdatedAt(): String {
        return updated_at
    }

    fun setUpdatedAt(updated_at: String) {
        this.updated_at = updated_at
    }

    override fun getUsername(): String {
        return username
    }

    override fun setUsername(username: String) {
        this.username = username
    }

    override fun getEmail(): String {
        return email
    }

    override fun setEmail(email: String) {
        this.email = email
    }

    override fun getAccessToken(): String {
        return accessToken
    }

    override fun setAccessToken(accessToken: String) {
        this.accessToken = accessToken
    }
}