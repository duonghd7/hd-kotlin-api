package com.hdd.kotlinapi.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.support.annotation.UiThread
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.hdd.kotlinapi.utils.Constants.UNIQUE_KEY_DEVICE_ID
import com.orhanobut.hawk.Hawk

/**
 * Created on 4/9/2018.
 *
 * @author duonghd
 */

@SuppressLint("ClickableViewAccessibility")
object AppUtils {

    fun isConnectedToNetwork(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    /*
    * hide keyboard when press to out input view
    * */
    fun setupUI(view: View, context: Context) {
        if (view !is EditText) {
            view.setOnTouchListener { v, event ->
                hideKeyboard(view, context)
                false
            }
        }
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupUI(innerView, context)
            }
        }
    }

    fun hideKeyboard(view: View, context: Context?) {
        if (context == null)
            throw RuntimeException("Context is null, init(Context)")
        val mgr = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mgr.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /*
    * keyboard hide -> clear focus
    * */
    fun setupKeyboard(activity: Activity) {
        KeyboardVisibility.setEventListener(activity, { isOpen ->
            (
                    if (!KeyboardVisibility.isKeyboardVisible(activity) && activity.currentFocus != null) {
                        activity.currentFocus!!.clearFocus()
                    })
        })
    }

    /*
    * get device token
    * */
    @UiThread
    fun getDeviceToken(): String {
        if (Hawk.isBuilt() && Hawk.contains(UNIQUE_KEY_DEVICE_ID)) {
            return Hawk.get(UNIQUE_KEY_DEVICE_ID)
        } else {
            return ""
        }
    }
}
