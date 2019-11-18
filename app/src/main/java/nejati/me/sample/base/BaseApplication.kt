package nejati.me.sample.base

import android.app.Application
import nejati.me.service.di.component.DaggerNetComponent
import nejati.me.service.di.module.NetModule
import nejati.me.service.generator.SingletonService
import nejati.me.service.helper.Const

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class BaseApplication : Application() {
    companion object {
        private lateinit var app: BaseApplication

        fun get(): BaseApplication = app
    }
    override fun onCreate() {
        super.onCreate()
        app = this
        val mNetComponent = DaggerNetComponent.builder()
            .netModule(NetModule(Const.BASEURl))
            .build()
        SingletonService.instance.setNetComponent(mNetComponent).inject()
    }
}
