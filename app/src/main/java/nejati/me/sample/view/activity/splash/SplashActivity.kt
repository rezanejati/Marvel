package nejati.me.sample.view.activity.splash

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splash.*
import nejati.me.sample.BR
import nejati.me.sample.R
import nejati.me.sample.base.BaseActivity
import nejati.me.sample.databinding.ActivitySplashBinding
import nejati.me.sample.view.activity.comics.ComicsListActivity
import nejati.me.sample.viewModel.splash.SplashViewModel
import android.animation.ObjectAnimator
import android.view.View
import kotlinx.android.synthetic.main.activity_splash.root
import android.util.DisplayMetrics
import android.graphics.Point

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(),
    SplashActivityNavigator {



    var height: Float? = null

    var width: Float? = null

    /**
     *
     * Update UI when Network isn't aviliable
     */
    override fun onNetworkError() {
        showSnackBar(root, getString(R.string.no_internet_connection))

    }


    /**
     * Set Variable fot DataBinding
     *
     * @return
     */
    override val bindingVariable: Int
        get() = BR.viewModel

    /**
     * Set Layout For Activity
     *
     * @return
     */
    override val layoutRes: Int
        get() = R.layout.activity_splash

    /**
     * Add View Model
     *
     * @return
     */
    override fun getViewModel(): Class<SplashViewModel> {
        return SplashViewModel::class.java
    }


    /**
     * When all the animations are end
     */

    override fun onAnimationEnd() {
        startActivity(Intent(this@SplashActivity, ComicsListActivity::class.java))
        finish()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel!!.navigator = this

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        height = displayMetrics.heightPixels.toFloat()
        width = displayMetrics.widthPixels.toFloat()

        val display = windowManager.defaultDisplay
        val point = Point()
        display.getSize(point)
        width = point.x.toFloat()
        height = point.y.toFloat()

        startAnimations()
    }


    // Create Animation for Splash
    fun startAnimations() {

        val slideInLeft = ObjectAnimator.ofFloat(ivMarvelLogo, View.TRANSLATION_X, -width!!, 0f)
        slideInLeft.setDuration(700)
        slideInLeft.start()


        val slideInUp = ObjectAnimator.ofFloat(ivComicsLogo, View.TRANSLATION_Y, height!!, 0f)
        slideInUp.setDuration(700)
        slideInUp.start()

        val slideInDown = ObjectAnimator.ofFloat(tvAuthorName, View.TRANSLATION_Y, -height!!, 0f)
        slideInDown.setDuration(1200)
        slideInDown.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                shakeAnimation()
            }
        })
        slideInDown.start()

    }

    fun shakeAnimation() {
        val shake = ObjectAnimator.ofFloat(
            ivMarvelLogo,
            "translationX",
            0f,
            25f,
            -25f,
            25f,
            -25f,
            15f,
            -15f,
            6f,
            -6f,
            0f
        )
        shake.setDuration(400)
        shake.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                Handler().postDelayed({ endAnimations() }, 3000)

            }
        })
        shake.start()
    }

    fun endAnimations() {
        val slideOutLeft = ObjectAnimator.ofFloat(ivMarvelLogo, View.TRANSLATION_X, 0f, -width!!)
        slideOutLeft.setDuration(500)
        slideOutLeft.start()

        val slideOutDown = ObjectAnimator.ofFloat(ivComicsLogo, View.TRANSLATION_Y, 0f, height!!)
        slideOutDown.setDuration(500)
        slideOutDown.start()

        val slideOutUp = ObjectAnimator.ofFloat(tvAuthorName, View.TRANSLATION_Y, 0f, -height!!)
        slideOutUp.setDuration(500)
        slideOutUp.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                onAnimationEnd()
            }
        })
        slideOutUp.start()
    }

}
