package nejati.me.sample.view.dialog

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import java.util.Objects
import nejati.me.service.model.comics.response.Result
import nejati.me.sample.BR
import nejati.me.sample.base.BaseDialog
import nejati.me.sample.databinding.AlertDialogDetailBinding
import nejati.me.sample.viewModel.dialog.detailDialog.DialogViewModel
import nejati.me.sample.R



/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class DetailDialog(override val _Activity: Activity) :
    BaseDialog<AlertDialogDetailBinding, DialogViewModel>(), DetailDialogNavigator {
    private var result: Result? = null

    companion object {
        @JvmStatic fun newInstance(activity: Activity, result: Result): DetailDialog {
            val dialog = DetailDialog(activity)
            dialog.result = result
            return dialog
        }

    }

    override fun getViewModel(): Class<DialogViewModel> {
        return DialogViewModel::class.java
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutRes_portrait: Int
        get() = R.layout.alert_dialog_detail

    override val layoutRes_land: Int
        get() = R.layout.alert_dialog_detail_land

    override fun onCreate() {

        viewModel!!.navigator = this

        viewModel!!.comicsItem.set(result)

        Objects.requireNonNull(dialog.window).setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val lp = WindowManager.LayoutParams()

        lp.copyFrom(dialog.window!!.attributes)

        lp.width = WindowManager.LayoutParams.MATCH_PARENT

        lp.height = WindowManager.LayoutParams.MATCH_PARENT

        dialog.window!!.attributes = lp


    }

    override fun onCloseClick() {
        dismiss()
    }


    override fun onShareClick() {

        val sharingIntent = Intent(Intent.ACTION_SEND)

        sharingIntent.type = "text/plain"

        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.this_is_comics_detail))

        val sb = StringBuilder()

        sb.append(getString(R.string.title_with).plus(result!!.title).plus("\n"))
            .append(
                getString(R.string.description_with).plus(
                    if (result!!.description == null) getString(R.string.there_is_no_description)
                    else result!!.description
                ).plus("\n")
            )

            .append(getString(R.string.issue_number_with).plus(result!!.issueNumber).plus("\n"))

            .append(getString(R.string.page_number_with).plus(result!!.pageCount).plus("\n"))

            .append(result!!.thumbnail!!.path + "." + result!!.thumbnail!!.extension)

        sharingIntent.putExtra(Intent.EXTRA_TEXT, sb.toString())

        startActivity(
            Intent.createChooser(sharingIntent, "share").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}
