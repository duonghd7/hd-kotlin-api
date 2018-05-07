package com.hdd.kotlinapi.domain.home

import com.hdd.kotlinapi.domain.base.BaseView
import com.hdd.kotlinapi.infastructures.models.account.User

/**
 * Created on 4/27/2018.
 * @author duonghd
 */

interface HomeView : BaseView {
    fun authMeSuccess(user: User)

    fun logoutSuccess()
}