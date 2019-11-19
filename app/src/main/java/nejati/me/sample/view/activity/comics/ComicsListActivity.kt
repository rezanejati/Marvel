package nejati.me.sample.view.activity.comics

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_comics_list.*
import nejati.me.sample.BR
import nejati.me.sample.base.BaseActivity
import nejati.me.sample.viewModel.comicsList.ComicsViewModel
import nejati.me.service.model.comics.request.ComicsRequestModel
import nejati.me.service.model.comics.response.Result
import nejati.me.sample.utility.MyScrollListener
import android.view.MenuItem
import android.app.SearchManager
import android.content.Context
import android.content.res.Configuration
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import nejati.me.sample.BuildConfig
import nejati.me.sample.R
import nejati.me.sample.databinding.ActivityComicsListBinding
import nejati.me.sample.view.dialog.DetailDialog
import nejati.me.sample.utility.Tools
import nejati.me.sample.utility.DisposableManager

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class ComicsListActivity() :
    BaseActivity<ActivityComicsListBinding, ComicsViewModel>(),
    ComicsListActivityNavigator, SearchView.OnQueryTextListener {

    var searchView: SearchView? = null

    var detailDialog: DetailDialog? = null

    /**
     * If search have any result onFoundResultSearch will call
     *
     * @param size of result
     */
    override fun onFoundResultSearch(size: Int) {
        showSnackBar(root, size.toString().plus(" ").plus(getString(R.string.item_found)))
    }

    /**
     *If search haven't any result onNoResultSearch will be call
     *
     */
    override fun onNoResultSearch() {
        showSnackBar(root, getString(R.string.no_result_found))
    }

    /**
     *Clear comics recycler view adapter
     *
     */
    override fun clearComicsAdapter() {
        rvComics.adapter = null
    }

    /**
     *
     * Update UI when network isn't aviliable
     */
    override fun onNetworkError() {
        showSnackBar(root, getString(R.string.no_internet_connection))
    }


    /**
     *
     * Show pagination progress
     */
    override fun onShowPaginationProgress() {
        viewModel!!.showPaginationProgress.set(true)
    }

    /**
     *
     * Hide pagination progress
     */
    override fun onHidePaginationProgress() {
        viewModel!!.showPaginationProgress.set(false)
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
        get() = R.layout.activity_comics_list

    /**
     * Add View Model
     *
     * @return
     */
    override fun getViewModel(): Class<ComicsViewModel> {
        return ComicsViewModel::class.java
    }


    /**
     * Handle FloatingActionButton click
     *
     */
    override fun onFabClick() {
        rvComics.smoothScrollToPosition(0)
    }

    /**
     * When click on recycler view item you can get the comicsItem
     *
     * @param comicsItem ComicsItem
     */
    override fun onDetail(comicsItem: Result) {
        detailDialog = DetailDialog.newInstance(this, comicsItem)
        detailDialog!!.show(supportFragmentManager, "detailDialog")
    }

    /**
     * Server error
     **/
    override fun onServerError() {
        viewModel!!.showRetryLayout.set(true)
    }

    /**
     * Hide Web Service Progress
     **/
    override fun onHideProgress() {
        viewModel!!.showProgressLayout.set(false)
    }

    /**
     * Show Web Service Progress
     **/
    override fun onShowProgress() {
        viewModel!!.showProgressLayout.set(true)
        viewModel!!.showRetryLayout.set(false)
    }

    /**
     * Create Comics Request For Web Service
     * @return ComicsRequestModel
     */
    fun createComicsRequest(): ComicsRequestModel {
        val comicsRequestModel = ComicsRequestModel()
        val timestamp: Long
        timestamp = System.currentTimeMillis()
        comicsRequestModel.ts = timestamp
        comicsRequestModel.apikey = BuildConfig.PUBLIC_KEY
        comicsRequestModel.hash = Tools.marvelHashGenaretor(timestamp)
        comicsRequestModel.format = "comic"
        comicsRequestModel.formatType = "comic"
        comicsRequestModel.limit = 10
        comicsRequestModel.orderBy = "-modified"
        comicsRequestModel.offset = 0
        return comicsRequestModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        screenStatus(resources.configuration.orientation)

        viewModel!!.navigator = this

        //Call webservice
        viewModel!!.requestModel = createComicsRequest()
        viewModel!!.callComics()

        //Init Toolbar
        setSupportActionBar(toolbar)
        setTitle(getString(R.string.app_name))


        //Live Data for Adapter
        viewModel!!.comicsListLiveData.observe(this, Observer<List<Result>> { s ->
            viewModel!!.setComicsList(s)
        })


        //ScrollListener for Comics RecyclerView
        rvComics.addOnScrollListener(object : MyScrollListener(this) {

            // End Scroll: Call WebService for next page
            override fun onEnd() {
                // When the user is searching in the comics list apiInPagintion is true
                // anf WebService won't call
                if (viewModel!!.apiCall.get()!!) {
                    viewModel!!.callComicsWebServiceForNextPage()
                    onShowPaginationProgress()
                }
            }

            // On Move Scroll: Hide Fab Button
            override fun onFirst() {
                fabButton.animate().translationY(fabButton.getHeight().toFloat()*2).setListener(object :
                    AnimatorListenerAdapter() {
                    @SuppressLint("RestrictedApi")
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        fabButton.visibility=View.GONE
                    }
                })
            }

            // On Move Scroll: Hide Toolbar and Fab Button
            @SuppressLint("RestrictedApi")
            override fun onMoved(distance: Int, dy: Int) {
                toolbar.setTranslationY((-distance).toFloat())
                fabButton.setTranslationY(((distance) * 1.4).toFloat())

                if (dy<0 && fabButton.visibility==View.GONE){
                    fabButton.visibility=View.VISIBLE
                }
            }
        })
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.getItemId()
        return if (id == R.id.action_search) {
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView = menu.findItem(R.id.action_search)
            .getActionView() as SearchView

        searchView!!.setSearchableInfo(
            searchManager.getSearchableInfo(componentName)
        )

        searchView!!.setMaxWidth(Integer.MAX_VALUE)

        searchView!!.setOnQueryTextListener(this)

        return true
    }

    // filter comics list
    override fun onQueryTextSubmit(query: String?): Boolean {

        if (query!!.length > 2) {

            viewModel!!.apiCall.set(false)

            viewModel!!.comicsFilter(query)

        }
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {

        if (query!!.length == 0) {

            viewModel!!.apiCall.set(true)

            viewModel!!.comicsListLiveData.setValue(viewModel!!.comicsList)

        }
        return false
    }


    override fun onDestroy() {
        DisposableManager.dispose()
        super.onDestroy()
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (detailDialog != null && detailDialog!!.isAdded) {
            detailDialog!!.dismiss()
            detailDialog!!.show(supportFragmentManager, "detailDialog")
        }

        screenStatus(newConfig.orientation)
    }

    fun screenStatus(orientation: Int) {

        val i = (rvComics.getLayoutManager() as LinearLayoutManager).findFirstVisibleItemPosition()

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvComics.setLayoutManager(LinearLayoutManager(this))
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvComics.setLayoutManager(GridLayoutManager(this, 2))
        }

        rvComics.scrollToPosition(i)

    }

}
