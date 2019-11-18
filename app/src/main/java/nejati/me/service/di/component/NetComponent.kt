package nejati.me.service.di.component

import javax.inject.Singleton
import dagger.Component
import nejati.me.service.di.module.NetModule
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
@Singleton
@Component(modules = [NetModule::class])
interface NetComponent {

    fun retrofit(): Retrofit

    fun okhttp(): OkHttpClient

}

