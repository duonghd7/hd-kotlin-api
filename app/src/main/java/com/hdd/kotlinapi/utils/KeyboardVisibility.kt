package com.hdd.kotlinapi.utils

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver

/**
 * Created on 4/9/2018.
 * @author duonghd
 */

object KeyboardVisibility {
    private val KEYBOARD_VISIBLE_THRESHOLD_DP = 100

    /**
     * Set keyboard visibility change event listener.
     *
     * @param activity Activity
     * @param listener KeyboardVisibilityEventListener
     */
    fun setEventListener(activity: Activity?, listener: (isOpen: Boolean) -> Unit?) {
        if (activity == null) {
            throw NullPointerException("Parameter:activity must not be null")
        }

        val activityRoot = getActivityRoot(activity)

        activityRoot.viewTreeObserver.addOnGlobalLayoutListener(
                object : ViewTreeObserver.OnGlobalLayoutListener {

                    private val r = Rect()

                    private val visibleThreshold = Math.round(
                            convertDpToPx(activity, KEYBOARD_VISIBLE_THRESHOLD_DP.toFloat()))

                    private var wasOpened = false

                    override fun onGlobalLayout() {
                        activityRoot.getWindowVisibleDisplayFrame(r)

                        val heightDiff = activityRoot.rootView.height - r.height()

                        val isOpen = heightDiff > visibleThreshold

                        if (isOpen == wasOpened) {
                            // keyboard state has not changed
                            return
                        }
                        wasOpened = isOpen
                        listener(isOpen)
                    }
                })
    }

    /**
     * Determine if keyboard is visible
     *
     * @param activity Activity
     * @return Whether keyboard is visible or not
     */
    fun isKeyboardVisible(activity: Activity): Boolean {
        val r = Rect()

        val activityRoot = getActivityRoot(activity)
        val visibleThreshold = Math
                .round(convertDpToPx(activity, KEYBOARD_VISIBLE_THRESHOLD_DP.toFloat()))

        activityRoot.getWindowVisibleDisplayFrame(r)

        val heightDiff = activityRoot.rootView.height - r.height()

        return heightDiff > visibleThreshold
    }

    private fun getActivityRoot(activity: Activity): View {
        return (activity.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)
    }

    fun convertDpToPx(context: Context?, dp: Float): Float {
        val res = context!!.resources
        return dp * (res.displayMetrics.densityDpi / 160f)
    }
}