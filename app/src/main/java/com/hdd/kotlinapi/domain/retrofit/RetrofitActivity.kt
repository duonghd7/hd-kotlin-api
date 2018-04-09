package com.hdd.kotlinapi.domain.retrofit

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.hdd.kotlin_caf.exceptions.ApiThrowable
import com.hdd.kotlinapi.MainApplication
import com.hdd.kotlinapi.R
import com.hdd.kotlinapi.infastructures.models.account.LoginRequestBody
import com.hdd.kotlinapi.infastructures.module.ActivityModule
import com.hdd.kotlinapi.services.authentication.AuthenticationService
import com.hdd.kotlinapi.utils.AppUtils
import org.androidannotations.annotations.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created on 3/1/2018.
 * @author duonghd
 */

@SuppressLint("Registered,SetTextI18n")
@EActivity(R.layout.activity_retrofit)
open class RetrofitActivity : AppCompatActivity() {
    @App
    lateinit var mainApplication: MainApplication
    @Inject
    lateinit var authService: AuthenticationService
    /*@Inject
    lateinit var provinceService: ProvinceService*/

    @ViewById(R.id.activity_retrofit_ll_root)
    lateinit var llRoot: LinearLayout
    @ViewById(R.id.activity_retrofit_edt_username)
    lateinit var edtUsername: EditText
    @ViewById(R.id.activity_retrofit_edt_password)
    lateinit var edtPassword: EditText
    @ViewById(R.id.activity_retrofit_tv_user_info)
    lateinit var tvUserInfo: TextView

    @AfterInject
    fun initInject() {
        DaggerRetrofitComponent.builder()
                .applicationComponent(mainApplication.getApplicationComponent())
                .activityModule(ActivityModule(this))
                .build().inject(this)
    }

    @AfterViews
    fun init() {
        AppUtils.setupUI(llRoot, this)
        AppUtils.setupKeyboard(this)
    }

    @Click(R.id.activity_retrofit_tv_login)
    fun tvLoginClick() {
        tvUserInfo.text = "Loading"
        authService.login(LoginRequestBody(edtUsername.text.toString(), edtPassword.text.toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    tvUserInfo.text = Gson().toJson(it).toString()
                }, { it: Throwable? ->
                    it as ApiThrowable
                    tvUserInfo.text = "Error code: ${it.firstErrorCode()} \nError mess: ${it.firstErrorMessage()}"
                })
    }

    @Click(R.id.activity_retrofit_tv_logout)
    fun tvLogoutClick() {
        authService.logout()
        tvUserInfo.text = authService.getLoginResponse().toString()
    }

    @Click(R.id.activity_retrofit_tv_load)
    fun tvLoadClick() {
        if (authService.isLoadFromStorageSuccess()) {
            tvUserInfo.text = Gson().toJson(authService.getLoginResponse()).toString()
        }
    }

/*    @Click(R.id.activity_retrofit_tv_get_province)
    fun tvGetProvinceClick() {
        tvUserInfo.text = "Loading"
        provinceService.getProvince()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    tvUserInfo.text = Gson().toJson(it).toString()
                }, { it: Throwable? ->
                    Log.e("test", "fail")
                })
    }*/

    override fun onBackPressed() {
        finish()
    }
}