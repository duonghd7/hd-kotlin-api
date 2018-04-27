package com.hdd.kotlinapi.domain.login

import com.hdd.kotlinapi.domain.base.BaseView
import com.hdd.kotlinapi.infastructures.models.account.User

/**
 * Created on 4/26/2018.
 * @author duonghd
 */

interface LoginView : BaseView {
    fun clearGcmSuccess()

    fun updateGcmSuccess()
}