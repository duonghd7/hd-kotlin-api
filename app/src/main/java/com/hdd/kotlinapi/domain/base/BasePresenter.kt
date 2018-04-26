package com.hdd.kotlinapi.domain.base

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.MvpView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

/**
 * Created on 4/26/2018.
 * @author duonghd
 */

abstract class BasePresenter<V : MvpView> : MvpBasePresenter<V>(), MvpPresenter<V> {

    @Inject
    protected lateinit var eventBus: EventBus

    @Inject
    fun BasePresenter(eventBus: EventBus) {
        this.eventBus = eventBus
    }

    @Subscribe
    override fun attachView(view: V) {
        super.attachView(view)
        eventBus.register(this)
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
        eventBus.unregister(this)
    }
}