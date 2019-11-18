package nejati.me.sample.viewModel.splash

import nejati.me.sample.base.ActivityBaseViewModel
import nejati.me.sample.view.activity.splash.SplashActivityNavigator

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class SplashViewModel : ActivityBaseViewModel<SplashActivityNavigator>(){

    override fun isInternetAvilable(status: Boolean) {
        if (!status) {
            navigator!!.onNetworkError();
        }
    }

    override fun OnClickRetryAction() {
        //Todo Handle WebService
    }
}


