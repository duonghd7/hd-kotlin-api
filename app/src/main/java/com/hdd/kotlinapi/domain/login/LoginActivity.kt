package com.hdd.kotlinapi.domain.login

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.hdd.kotlinapi.MainApplication
import com.hdd.kotlinapi.R
import com.hdd.kotlinapi.adapter.PagerAdapter
import com.hdd.kotlinapi.domain.base.BaseActivity
import com.hdd.kotlinapi.domain.login.fragment.SignInFragment_
import com.hdd.kotlinapi.domain.login.fragment.SignUpFragment_
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
    lateinit var presenter: LoginPresenter

    @AfterInject
    protected fun initInject() {

    }

    override fun createPresenter(): LoginPresenter {
        return getPresenter()
    }

    override fun showLoading() {
        showHUD()
    }

    override fun hideLoading() {
        hideHUD()
    }

    override fun apiError(throwable: Throwable) {

    }

    @AfterViews
    protected fun init() {
        var fragments: List<Fragment> = emptyList()
        fragments += SignInFragment_.builder().build()
        fragments += SignUpFragment_.builder().build()

        vpViewPager.adapter = PagerAdapter(supportFragmentManager, fragments)
        vpViewPager.offscreenPageLimit = fragments.size
    }

    override fun onGoSignInClick() {
        vpViewPager.setCurrentItem(0, true)
    }

    override fun onGoSignUpClick() {
        vpViewPager.setCurrentItem(1, true)
    }

    override fun onBackPressed() {
        finish()
    }
}