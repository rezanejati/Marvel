package nejati.me.sample.base

import android.app.Activity
import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import nejati.me.sample.di.component.DaggerApplicationComponent


class BaseApplication : Application(), HasActivityInjector, LifecycleObserver {

    companion object {
        private lateinit var app: BaseApplication
        fun get(): BaseApplication = app
    }

    @set : Inject
    var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);

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