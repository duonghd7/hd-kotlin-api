package com.hdd.kotlinapi.domain.base

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.hdd.kotlin_caf.base.DBasePresenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

/**
 * Created on 4/26/2018.
 * @author duonghd
 */

abstract class BasePresenter<V : MvpView> : DBasePresenter<V>()