package nejati.me.sample.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import nejati.me.sample.base.BaseApplication
import nejati.me.sample.di.module.ActivityBindingModule
import nejati.me.sample.di.module.ApiModule
import nejati.me.sample.di.module.ApplicationModule
import nejati.me.sample.di.module.RxModule
import nejati.me.sample.di.scope.CustomScope

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
@CustomScope
@Component(modules = [ApplicationModule::class, AndroidSupportInjectionModule::class,
    ActivityBindingModule::class, ApiModule::class, RxModule::class])
interface ApplicationComponent : AndroidInjector<BaseApplication> {

    override fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApplication): Builder

        fun build(): ApplicationComponent
    }
}