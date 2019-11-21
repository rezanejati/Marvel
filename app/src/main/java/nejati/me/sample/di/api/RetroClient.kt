package nejati.me.sample.di.api

import io.reactivex.Single
import nejati.me.service.helper.Const
import nejati.me.service.model.comics.response.ComicsResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
interface RetroClient {

    @GET(Const.COMICS)
    fun getComicsList(@Query("ts") ts: Long,
                      @Query("apikey") apikey: String,
                      @Query("hash") hash: String,
                      @Query("format") format: String,
                      @Query("formatType") formatType: String,
                      @Query("limit") limit: Int,
                      @Query("orderBy") orderBy: String,
                      @Query("offset") offset: Int): Single<ComicsResponseModel>
}
