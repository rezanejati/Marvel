package nejati.me.service.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import nejati.me.sample.base.BaseApplication
import nejati.me.sample.base.StaticValue
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
@Module
class NetModule

/**
 * @param mBaseUrl
 */
    (private val mBaseUrl: String) {

    /**
     * Initialize  GsonBuilder
     * @return the GsonBuilder
     */
    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    /**
     * Initialize  OkHttpClient
     * @return the OkHttpClient.Builder
     */
    @Provides
    @Singleton
    internal fun provideOkhttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.connectTimeout(StaticValue.TimeOut, TimeUnit.SECONDS)
        client.readTimeout(StaticValue.TimeOut, TimeUnit.SECONDS)
        client.writeTimeout(StaticValue.TimeOut, TimeUnit.SECONDS)
        return client.build()
    }

    /**
     * Initialize  Retrofit
     * @param gson
     * @param okHttpClient
     * @return the Retrofit
     */
    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(mBaseUrl)
            .client(okHttpClient)
            .build()
    }

}

