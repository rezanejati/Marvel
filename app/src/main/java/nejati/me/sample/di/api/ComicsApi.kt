package nejati.me.sample.di.api


import javax.inject.Inject

import io.reactivex.Single
import nejati.me.service.model.comics.request.ComicsRequestModel
import nejati.me.service.model.comics.response.ComicsResponseModel

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class ComicsApi
@Inject
constructor(private val api: RetroClient) {

    fun getComics(request: ComicsRequestModel): Single<ComicsResponseModel> {
        return api.getComicsList(request.ts!!,
            request.apikey!!,
            request.hash!!,
            request.format!!,
            request.formatType!!,
            request.limit!!,
            request.orderBy!!,
            request.offset!!)
    }

}
