package com.hdd.kotlinapi.domain.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import com.hdd.kotlinapi.R

/**
 * Created on 5/4/2018.
 * @author duonghd
 */

open class LoadingDialog(context: Context) : Dialog(context, R.style.dialog_style) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
    }
}