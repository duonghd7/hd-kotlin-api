package com.hdd.kotlinapi.domain.login.fragment.sign_up

import android.support.v4.app.Fragment
import com.hdd.kotlinapi.R
import com.hdd.kotlinapi.domain.login.LoginActivityModel
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment

/**
 * Created on 4/24/2018.
 * @author duonghd
 */

@EFragment(R.layout.fragment_sign_up)
open class SignUpFragment : Fragment() {

    private lateinit var loginActivityModel: LoginActivityModel

    @AfterViews
    fun init() {
        loginActivityModel = this.context as LoginActivityModel
    }

    @Click(R.id.fragment_sign_up_tv_sign_in)
    fun goSignIn(){
        loginActivityModel.onGoSignInClick()
    }
}