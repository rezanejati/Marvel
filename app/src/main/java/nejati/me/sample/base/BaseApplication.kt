package nejati.me.sample.base

import android.app.Activity
import android.app.Application

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import nejati.me.sample.di.component.DaggerApplicationComponent


class BaseApplication : Application(), HasActivityInjector {

    companion object {
        private lateinit var app: BaseApplication
        fun get(): BaseApplication = app
    }

    @set : Inject
    var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null

    override fun onCreate() {
        super.onCreate()

        app = this

        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }
}