package com.hdd.kotlinapi.domain.login

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.widget.Toast
import com.hdd.kotlinapi.MainApplication
import com.hdd.kotlinapi.R
import com.hdd.kotlinapi.adapter.PagerAdapter
import com.hdd.kotlinapi.domain.base.BaseActivity
import com.hdd.kotlinapi.domain.login.fragment.sign_in.SignInFragment_
import com.hdd.kotlinapi.domain.login.fragment.sign_up.SignUpFragment_
import com.hdd.kotlinapi.infastructures.models.account.LoginRequestBody
import com.hdd.kotlinapi.infastructures.models.account.User
import com.hdd.kotlinapi.infastructures.module.ActivityModule
import org.androidannotations.annotations.*
import javax.inject.Inject


/**
 * Created on 4/24/2018.
 * @author duonghd
 */

@SuppressLint("Registered")
@EActivity(R.layout.acitivity_login)
open class LoginActivity : BaseActivity<LoginView, LoginPresenter>(), LoginView, LoginActivityModel {

    @ViewById(R.id.activity_login_vp_pager)
    protected lateinit var vpViewPager: ViewPager

    @App
    protected lateinit var mainApplication: MainApplication
    @Inject
    protected lateinit var loginPresenter: LoginPresenter

    @AfterInject
    protected fun initInject() {
        DaggerLoginComponent.builder()
                .applicationComponent(mainApplication.getApplicationComponent())
                .activityModule(ActivityModule(this))
                .build().inject(this)
    }

    override fun createPresenter(): LoginPresenter {
        return loginPresenter
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

    @AfterViews
    protected fun init() {
        var fragments: List<Fragment> = emptyList()
        fragments += SignInFragment_.builder().build()
        fragments += SignUpFragment_.builder().build()

        vpViewPager.adapter = PagerAdapter(supportFragmentManager, fragments)
        vpViewPager.offscreenPageLimit = fragments.size

        loginPresenter.clearGcm()
    }

    override fun clearGcmSuccess() {
        Toast.makeText(this, "Clear success", Toast.LENGTH_SHORT).show()
    }

    override fun updateGcmSuccess() {
        Toast.makeText(this, "Update success", Toast.LENGTH_SHORT).show()
    }

    override fun onGoSignInClick() {
        vpViewPager.setCurrentItem(0, true)
    }

    override fun onGoSignUpClick() {
        vpViewPager.setCurrentItem(1, true)
    }

    override fun onUpdateGcmToken() {
        loginPresenter.updateGcm()
    }

    override fun onBackPressed() {
        finish()
    }
}