package com.hdd.kotlinapi.domain.login

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.hdd.kotlinapi.R
import com.hdd.kotlinapi.adapter.PagerAdapter
import com.hdd.kotlinapi.domain.login.fragment.SignInFragment_
import com.hdd.kotlinapi.domain.login.fragment.SignUpFragment_
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById

/**
 * Created on 4/24/2018.
 * @author duonghd
 */

@SuppressLint("Registered")
@EActivity(R.layout.acitivity_login)
open class LoginActivity : AppCompatActivity(), LoginActivityModel {

    @ViewById(R.id.activity_login_vp_pager)
    protected lateinit var vpViewPager: ViewPager

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