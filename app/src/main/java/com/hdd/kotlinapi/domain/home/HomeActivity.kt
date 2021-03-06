package com.hdd.kotlinapi.domain.home

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.TextView
import com.google.gson.Gson
import com.hdd.kotlinapi.MainApplication
import com.hdd.kotlinapi.R
import com.hdd.kotlinapi.domain.base.BaseActivity
import com.hdd.kotlinapi.domain.login.LoginActivity_
import com.hdd.kotlinapi.infastructures.models.account.User
import com.hdd.kotlinapi.infastructures.module.ActivityModule
import org.androidannotations.annotations.*
import javax.inject.Inject

/**
 * Created on 4/27/2018.
 * @author duonghd
 */

@SuppressLint("Registered")
@EActivity(R.layout.activity_home)
open class HomeActivity : BaseActivity<HomeView, HomePresenter>(), HomeView {

    @ViewById(R.id.activity_home_srl_refresh)
    protected lateinit var srlRefresh: SwipeRefreshLayout
    @ViewById(R.id.activity_home_tv_user_info)
    protected lateinit var tvUserInfo: TextView

    @App
    protected lateinit var mainApplication: MainApplication
    @Inject
    protected lateinit var homePresenter: HomePresenter

    @AfterInject
    fun initInject() {
        DaggerHomeComponent.builder()
                .applicationComponent(mainApplication.getApplicationComponent())
                .activityModule(ActivityModule(this))
                .build().inject(this)
    }

    override fun createPresenter(): HomePresenter {
        return homePresenter
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
    fun init() {
        if (!presenter.isLogined()) {
            LoginActivity_.intent(this)
                    .flags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .start()
            finish()
        } else {
            presenter.authMe()
        }

        setupUI(presenter.getUserInfo())

        srlRefresh.setColorSchemeColors(
                Color.parseColor("#ff0000"),
                Color.parseColor("#00ff00"),
                Color.parseColor("#0000ff"))
        srlRefresh.setOnRefreshListener {
            srlRefresh.isRefreshing = false
            presenter.authMe()
        }
    }

    private fun setupUI(user: User?) {
        if (user != null) {
            tvUserInfo.text = Gson().toJson(user).toString()
        }
    }

    @Click(R.id.activity_home_tv_logout)
    fun logoutClick() {
        presenter.logout()
    }

    override fun authMeSuccess(user: User) {
        setupUI(user)
    }

    override fun logoutSuccess() {
        LoginActivity_.intent(this)
                .flags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .start()
        finish()
    }

    override fun onBackPressed() {
        finish()
    }
}
