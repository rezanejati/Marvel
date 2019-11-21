package nejati.me.sample.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nejati.me.sample.utility.ComicsViewModelFactory
import javax.inject.Inject

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
abstract class BaseActivity<D : ViewDataBinding, V : ActivityBaseViewModel<*>> : AppCompatActivity()
{
    protected var dataBinding: D?=null

    protected var viewModel: V?=null

    abstract val bindingVariable: Int

    @set:Inject
    var comicsViewModelFactory: ComicsViewModelFactory? = null

    @get:LayoutRes
    protected abstract val layoutRes: Int

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        viewModel = ViewModelProviders.of(this,comicsViewModelFactory).get(getViewModel())
        dataBinding = DataBindingUtil.setContentView(this, layoutRes)
        dataBinding!!.setVariable(bindingVariable, viewModel)
        dataBinding!!.executePendingBindings()

        //Network listener
        ReactiveNetwork
            .observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isConnectedToInternet -> viewModel!!.isInternetAvilable(isConnectedToInternet!!) }
    }

    protected abstract fun getViewModel(): Class<V>

     fun showSnackBar( root : View,message : String){
        Snackbar.make(root,message, Snackbar.LENGTH_LONG)
            .show()
    }

}
