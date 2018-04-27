package com.hdd.kotlinapi.domain.login.fragment.sign_in

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.EditText
import com.hdd.kotlinapi.MainApplication
import com.hdd.kotlinapi.R
import com.hdd.kotlinapi.domain.base.BaseFragment
import com.hdd.kotlinapi.domain.login.LoginActivityModel
import com.hdd.kotlinapi.infastructures.models.account.LoginRequestBody
import com.hdd.kotlinapi.infastructures.module.ActivityModule
import org.androidannotations.annotations.*
import javax.inject.Inject

/**
 * Created on 4/24/2018.
 * @author duonghd
 */

@SuppressLint("Registered")
@EFragment(R.layout.fragment_sign_in)
open class SignInFragment : BaseFragment<SignInView, SignInPresenter>(), SignInView {

    @ViewById(R.id.fragment_sign_in_edt_username)
    protected lateinit var edtUsername: EditText
    @ViewById(R.id.fragment_sign_in_edt_password)
    protected lateinit var edtPassword: EditText


    @App
    protected lateinit var mainApplication: MainApplication
    @Inject
    protected lateinit var signInPresenter: SignInPresenter
    private lateinit var loginActivityModel: LoginActivityModel


    @AfterInject
    fun initInject() {
        DaggerSignInComponent.builder()
                .applicationComponent(mainApplication.getApplicationComponent())
                .activityModule(ActivityModule(activity as Activity))
                .build().inject(this)
    }

    override fun showLoading() {
        showHUD()
    }

    override fun hideLoading() {
        hideHUD()
    }

    override fun apiError(throwable: Throwable) {
        showError(throwable)
    }

    override fun createPresenter(): SignInPresenter {
        return signInPresenter
    }

    @AfterViews
    fun init() {
        loginActivityModel = this.context as LoginActivityModel
    }

    @Click(R.id.fragment_sign_in_tv_sign_in)
    fun loginClick() {
        presenter.login(LoginRequestBody(edtUsername.text.toString(), edtPassword.text.toString()))
    }

    @Click(R.id.fragment_sign_in_tv_sign_up)
    fun goSignUp() {
        loginActivityModel.onGoSignUpClick()
    }

    override fun loginSuccess() {
        loginActivityModel.onUpdateGcmToken()
    }
}