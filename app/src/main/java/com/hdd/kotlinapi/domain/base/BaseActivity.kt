package com.hdd.kotlinapi.domain.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.annotation.UiThread
import android.widget.Toast
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.MvpView
import com.hdd.kotlinapi.R
import com.hdd.kotlinapi.utils.AppUtils
import org.androidannotations.annotations.EActivity

/**
 * Created on 4/26/2018.
 * @author duonghd
 */

@EActivity
abstract class BaseActivity<V : MvpView, P : MvpPresenter<V>> : MvpActivity<V, P>() {

    private val networkChangeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            onNetworkChange(AppUtils.isConnectedToNetwork(context))
        }
    }

    private fun onNetworkChange(isConnected: Boolean) {
        if (!isConnected) {
            Toast.makeText(this, resources.getString(R.string.network_is_not_connect), Toast.LENGTH_LONG).show()
        }
    }

    @UiThread
    fun showHUD() {

    }

    @UiThread
    fun hideHUD() {

    }
}