package com.hdd.kotlinapi

/**
 * Created on 3/1/2018.
 * @author duonghd
 */

object ApiURL {
    private const val ROOT_LIVE_URL = "https://hd-firstsetup.herokuapp.com"
    private const val ROOT_DEV_URL = "http://172.16.1.7:8080"

    val ROOT_URL = if (BuildConfig.FLAVOR == "LIVE") ROOT_LIVE_URL else ROOT_DEV_URL
}