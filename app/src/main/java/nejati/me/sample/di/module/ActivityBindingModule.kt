package nejati.me.sample.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nejati.me.sample.view.activity.comics.ComicsListActivity
import nejati.me.sample.view.activity.splash.SplashActivity

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): ComicsListActivity

    @ContributesAndroidInjector
    internal abstract fun bindDetailActivity(): SplashActivity
}
