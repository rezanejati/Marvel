package nejati.me.sample.viewModel.comicsList

import android.annotation.SuppressLint
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nejati.me.sample.base.ActivityBaseViewModel
import nejati.me.sample.utility.DisposableManager
import nejati.me.sample.view.activity.comics.ComicsListActivityNavigator
import nejati.me.service.generator.SingletonService
import nejati.me.service.listener.OnServiceStatus
import nejati.me.service.model.comics.request.ComicsRequestModel
import nejati.me.service.model.comics.response.ComicsResponseModel
import nejati.me.service.model.comics.response.Result

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class ComicsViewModel : ActivityBaseViewModel<ComicsListActivityNavigator>(),
    OnServiceStatus<ComicsResponseModel> {

    /**
     * requestModel : this object is the ComicsRequest for webservice
     */
    var requestModel = ComicsRequestModel()

    /**
     * apiCall : this value when user search the list is false, that's mean api don't call
     */
    var apiCall = ObservableField(true)

    /**
     * showPaginationProgress : this value is boolean and when is true Pagination Progress will be
     * show
     */
    var showPaginationProgress = ObservableField<Boolean>()

    /**
     * showProgressLayout : this value is boolean and when is true peogress layout will be
     * show
     */
    var showProgressLayout = ObservableField(false)

    /**
     * comicsList : this object is the list of comics
     */
    val comicsList = ArrayList<Result>()

    /**
     * comicsListObservable : this is a ObservableArrayList and use in filtering
     */
    var comicsListObservable = ObservableArrayList<Result>()

    /**
     * comicsListObservable : this is a MutableLiveData and use in adapter
     */
    val comicsListLiveData = MutableLiveData<List<Result>>()


    /**
     *
     * @param position when click on recycler view item you can get the recycler view position
     */
    fun onComicsItemClick(position: Int) {
        navigator!!.onDetail(comicsListObservable.get(position))

    }

    /**
     *
     * @param status you can get the netwoek status via this method
     */
    override fun isInternetAvilable(status: Boolean) {
        if (!status) {
            navigator!!.onNetworkError();
        }
    }

    /**
     * the web service error message
     * @param message
     */
    override fun onError(message: String) {

        navigator!!.onHideProgress()

        navigator!!.onServerError()
    }

    /**
     * if Http Code Status not equal 200 This method will call
     * @param message
     */
    override fun onHttpCodeStatus(status: Int) {

        navigator!!.onHideProgress()

        navigator!!.onServerError()
    }


    /**
     * Comics Api Response
     * @param t Response Of ComicsResponse Api
     * @param statusCode Http Status Code
     */
    override fun onReady(t: ComicsResponseModel) {

        navigator!!.onHideProgress()

        navigator!!.onHidePaginationProgress()

        apiCall.set(true)

        comicsList.addAll(t.data!!.results!!)

        comicsListLiveData.setValue(comicsList)

    }


    /**
     * Call Comics Api
     */
    fun callComics() {

        navigator!!.onShowProgress()

        SingletonService.instance.marvelService().comicsService(this, requestModel)
    }


    fun callComicsWebServiceForNextPage() {

        apiCall.set(false)

        requestModel.offset = requestModel.offset!! + requestModel.limit!!

        SingletonService.instance.marvelService().comicsService(this, requestModel)
    }

    /**
     *When ComicsListLiveData is change, Update comicsListObservable and adapter will be notify
     * @param list ComicsItem
     */
    fun setComicsList(list: List<Result>) {

        comicsListObservable.clear()

        comicsListObservable.addAll(list)
    }


    override fun OnClickRetryAction() {

        callComics();

    }


    /**
     *  FloatingActionButton onClick
     **/
    fun OnClickFabAction() {

        navigator!!.onFabClick()

    }

    /**
     * Comics List Filter with input text
     *
     * @param text the user input
     */
    @SuppressLint("DefaultLocale")
    fun comicsFilter(text: String) {

        Observable.fromIterable(comicsListObservable)

            .observeOn(AndroidSchedulers.mainThread())

            .subscribeOn(Schedulers.computation())

            /**
             * Filter with comics name or You can add other filter
             */
            .filter({ x -> if (x.title!!.toLowerCase().contains(text.toLowerCase())) true else false })

            .toList()

            .subscribe(object : SingleObserver<List<Result>> {
                override fun onSubscribe(d: Disposable) {

                    DisposableManager.add(d)

                }

                override fun onSuccess(result: List<Result>) {

                    if (result.size > 0) {
                        navigator!!.clearComicsAdapter()
                        navigator!!.onFoundResultSearch(result.size)

                        comicsListLiveData.setValue(result)
                    } else {
                        navigator!!.onNoResultSearch()
                    }
                }

                override fun onError(e: Throwable) {
                    navigator!!.onNoResultSearch()
                }
            })
    }

}

