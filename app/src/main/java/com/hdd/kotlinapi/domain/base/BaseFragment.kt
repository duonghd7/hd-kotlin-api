package com.hdd.kotlinapi.domain.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.hdd.kotlin_caf.base.DBaseFragment
import com.hdd.kotlin_caf.exceptions.ApiThrowable
import com.hdd.kotlinapi.R
import com.hdd.kotlinapi.utils.AppUtils
import org.androidannotations.annotations.EFragment

/**
 * Created on 4/27/2018.
 * @author duonghd
 */

@EFragment
abstract class BaseFragment<V : MvpView, P : MvpPresenter<V>> : DBaseFragment<V, P>() {
    private var loadingDialog: LoadingDialog? = null

    private val networkChangeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            onNetworkChange(AppUtils.isConnectedToNetwork(context))
        }
    }

    private fun onNetworkChange(isConnected: Boolean) {
        if (!isConnected) {
            Toast.makeText(context, resources.getString(R.string.network_is_not_connect), Toast.LENGTH_LONG).show()
        }
    }

    override fun showHUD() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(context)
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
        Toast.makeText(context, throwable.firstErrorMessage(), Toast.LENGTH_SHORT).show()
    }
}