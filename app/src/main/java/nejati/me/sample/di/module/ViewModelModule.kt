package nejati.me.sample.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import nejati.me.sample.di.scope.ViewModelKey
import nejati.me.sample.utility.ComicsViewModelFactory
import nejati.me.sample.viewModel.comicsList.ComicsViewModel
import nejati.me.sample.viewModel.dialog.detailDialog.DialogViewModel
import nejati.me.sample.viewModel.splash.SplashViewModel

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ComicsViewModel::class)
    internal abstract fun comicsViewModel(comicsViewModel: ComicsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun splashViewModel(comicsViewModel: SplashViewModel): ViewModel

    @Binds
    internal abstract fun comicsViewModelFactory(factory: ComicsViewModelFactory): ViewModelProvider.Factory
}
