package nejati.me

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mock
import nejati.me.sample.di.api.ComicsApi
import nejati.me.sample.di.api.RxSingleSchedulers
import nejati.me.sample.viewModel.comicsList.ComicsViewModel
import nejati.me.service.model.comics.request.ComicsRequestModel

import nejati.me.service.model.comics.response.Result
import org.junit.Before
import org.mockito.Mockito

import org.mockito.MockitoAnnotations


/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class ViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    internal var apiClient: ComicsApi? = null

    private var viewModel: ComicsViewModel? = null

    @Mock
    internal var observer: Observer<List<Result>>? = null

    @Mock
    internal var lifecycleOwner: LifecycleOwner? = null

    internal var lifecycle: Lifecycle? = null

    @Mock
    internal var requestModel: ComicsRequestModel? = null


    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lifecycle = LifecycleRegistry(lifecycleOwner!!)
        viewModel = ComicsViewModel(apiClient!!, RxSingleSchedulers.TEST_SCHEDULER)
        viewModel!!.comicsListLiveData.observeForever(observer!!)
    }

    @Test
    fun nullTest() {
        Mockito.`when`(apiClient!!.getComics(requestModel!!)).thenReturn(null)
        assertNotNull(viewModel!!.comicsListLiveData)
        assertTrue(viewModel!!.comicsListLiveData.hasObservers())
    }


}
