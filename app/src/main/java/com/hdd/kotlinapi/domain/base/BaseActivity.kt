package com.hdd.kotlinapi.domain.base

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.hdd.kotlin_caf.base.DBaseActivity
import com.hdd.kotlin_caf.exceptions.ApiThrowable
import com.hdd.kotlinapi.R
import com.hdd.kotlinapi.utils.AppUtils
import org.androidannotations.annotations.EActivity

/**
 * Created on 4/26/2018.
 * @author duonghd
 */

@SuppressLint("Registered")
@EActivity
abstract class BaseActivity<V : MvpView, P : MvpPresenter<V>> : DBaseActivity<V, P>() {
    private var loadingDialog: LoadingDialog? = null

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

    override fun showHUD() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
            loadingDialog!!.setCancelable(false)
            loadingDialog!!.setCanceledOnTouchOutside(false)
            loadingDialog!!.show()
        } else if (!loadingDialog!!.isShowing) {
            loadingDialog!!.show()
        }
    }

    override fun hideHUD() {
        if (loadingDialog != null && loadingDialog!!.isShowing) {
            loadingDialog!!.dismiss()
        }
    }

    override fun showError(throwable: Throwable) {
        throwable as ApiThrowable
        Toast.makeText(this, throwable.firstErrorMessage(), Toast.LENGTH_SHORT).show()
    }
}