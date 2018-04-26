package com.hdd.kotlinapi

/**
 * Created on 3/1/2018.
 * @author duonghd
 */

object ApiURL {
    val ROOT_LIVE_URL = "https://hd-firstsetup.herokuapp.com"
    val ROOT_DEV_URL = "http://localhost:8080"

    val ROOT_URL = if (BuildConfig.FLAVOR == "LIVE") ROOT_LIVE_URL else ROOT_DEV_URL
}