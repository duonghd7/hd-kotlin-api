package com.hdd.kotlinapi.domain

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import com.hdd.kotlinapi.R
import com.hdd.kotlinapi.domain.retrofit.RetrofitActivity_
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity

@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
open class MainActivity : AppCompatActivity() {

    @AfterViews
    fun init() {

    }

    @Click(R.id.activity_main_tv_retrofit)
    fun tvRetrofitClick() {
        RetrofitActivity_.intent(this).start()
    }
}
