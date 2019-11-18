package nejati.me.service.part

import nejati.me.service.generator.ServiceGenerator
import nejati.me.service.listener.OnServiceStatus
import nejati.me.service.model.comics.request.ComicsRequestModel
import nejati.me.service.model.comics.response.ComicsResponseModel

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class MarvelService(serviceGenerator: ServiceGenerator) : BasePart(serviceGenerator) {

    override val part: BasePart
        get() = this

    /**
     * @param ts
     * @param apikey
     * @param hash
     * @param format
     * @param formatType
     * @param limit
     */
    fun comicsService(listener: OnServiceStatus<ComicsResponseModel>,
                      request: ComicsRequestModel) {
        start(serviceGenerator.createService().comicsBook(
            request.ts!!,
            request.apikey!!,
            request.hash!!,
            request.format!!,
            request.formatType!!,
            request.limit!!,
            request.orderBy!!,
            request.offset!!),listener)
    }
}