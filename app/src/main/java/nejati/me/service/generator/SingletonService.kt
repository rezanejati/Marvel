package nejati.me.service.generator

import javax.inject.Inject
import nejati.me.service.di.component.NetComponent
import nejati.me.service.part.MarvelService
import okhttp3.OkHttpClient

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */

class SingletonService private constructor() {

    private var netComponent: NetComponent? = null

    var serviceGenerator: ServiceGenerator? = null @Inject set

    var okHttpClient: OkHttpClient? = null @Inject set

    fun setNetComponent(netComponent: NetComponent): SingletonService {
        this.netComponent = netComponent
        return this
    }

    fun inject() {
        val componentService = DaggerComponentService.builder().netComponent(netComponent).build()
        componentService.inject(this)
    }

    fun marvelService(): MarvelService {
        return MarvelService(serviceGenerator!!)
    }

    companion object {
        val instance = SingletonService()
    }
}
