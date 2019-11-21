package nejati.me.sample.di.module

import dagger.Module
import dagger.Provides
import nejati.me.sample.di.api.RxSingleSchedulers
import nejati.me.sample.di.scope.CustomScope

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
@Module
class RxModule {

    @CustomScope
    @Provides
    fun providesScheduler(): RxSingleSchedulers {
        return RxSingleSchedulers.DEFAULT
    }

}
