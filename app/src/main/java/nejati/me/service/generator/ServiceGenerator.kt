package nejati.me.service.generator

import javax.inject.Inject
import nejati.me.service.api.RetroClient
import retrofit2.Retrofit

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class ServiceGenerator @Inject
constructor(private val retrofit: Retrofit) {

    fun createService(): RetroClient {
        return retrofit.create(RetroClient::class.java)
    }
}
