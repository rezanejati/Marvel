package nejati.me.sample.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
@Module(includes = [ViewModelModule::class])
abstract class ApplicationModule {

    @Binds
    internal abstract fun provideContext(application: Application): Context

}
