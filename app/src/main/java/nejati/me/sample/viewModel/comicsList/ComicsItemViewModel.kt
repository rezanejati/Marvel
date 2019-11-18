package nejati.me.sample.viewModel.comicsList

import androidx.databinding.ObservableField
import nejati.me.sample.view.adapter.CustomClickListener
import nejati.me.service.model.comics.response.Result

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class ComicsItemViewModel(
    result: Result?,
    private val customClickListener: CustomClickListener
) {
    var comicsItems = ObservableField<Result>()

    init {
        comicsItems.set(result)
    }

    fun onComicsClick(t: ComicsItemViewModel) {
        customClickListener.itemClicked(t)
    }

}
