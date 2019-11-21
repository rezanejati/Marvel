package nejati.me.sample.viewModel.splash

import nejati.me.sample.base.ActivityBaseViewModel
import nejati.me.sample.di.api.ComicsApi
import nejati.me.sample.di.api.RxSingleSchedulers
import nejati.me.sample.view.activity.splash.SplashActivityNavigator
import javax.inject.Inject

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class SplashViewModel() : ActivityBaseViewModel<SplashActivityNavigator>(){

    /**
     * inject retro client
     */
    @Inject
    constructor(api: ComicsApi, rxSingleSchedulers: RxSingleSchedulers) : this() {
        //Todo check comics web service is available

    }

    override fun isInternetAvilable(status: Boolean) {
        if (!status) {
            navigator!!.onNetworkError();
        }
    }

    override fun OnClickRetryAction() {
        //Todo Handle WebService
    }
}


