package nejati.me.sample.viewModel.dialog.detailDialog

import androidx.databinding.ObservableField
import nejati.me.sample.base.DialogBaseViewModel
import nejati.me.sample.view.dialog.DetailDialogNavigator
import nejati.me.service.model.comics.response.Result

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class DialogViewModel() : DialogBaseViewModel<DetailDialogNavigator>() {

    var comicsItem = ObservableField<Result>()

    var startAnimation = ObservableField(true)

    fun onClose() {
        navigator!!.onCloseClick()
    }

    fun onShare() {
        navigator!!.onShareClick()
    }

}
