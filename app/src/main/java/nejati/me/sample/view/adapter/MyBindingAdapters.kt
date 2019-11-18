package nejati.me.sample.view.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import nejati.me.sample.viewModel.comicsList.ComicsViewModel
import nejati.me.service.model.comics.response.Result
import android.view.animation.AnimationUtils
import nejati.me.sample.R
import nejati.me.sample.base.BaseApplication

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
object MyBindingAdapters {

    @JvmStatic@BindingAdapter("bind:recyclerAdapter", "bind:itemClick")
    fun addItems(
        recyclerView: RecyclerView,
        items: MutableList<Result>,
        comicsViewModel: ComicsViewModel) {

        if (recyclerView.adapter == null) {
            val adapter = ComicsListAdapter(items, comicsViewModel)
            recyclerView.adapter = adapter
        } else {
            recyclerView.adapter!!.notifyItemInserted(recyclerView.adapter!!.itemCount)
        }
    }

    @JvmStatic@BindingAdapter("comicsImage")
    fun loadImage(view: ImageView, imageUrl: String) {
        Glide.with(view.context).load(imageUrl).into(view)
    }
    @JvmStatic@BindingAdapter("detailImage")
    fun loadDetailImage(view: ImageView, imageUrl: String) {
        Glide.with(view.context).load(imageUrl).into(view)
    }


    @JvmStatic@BindingAdapter("startScaleUpAnimation")
    fun setAnimation(view: View, start: Boolean) {
        val animation = view.getAnimation()
        if (start && animation == null) {
            view.startAnimation(AnimationUtils.loadAnimation(BaseApplication.get(), R.anim.scale_up))
        } else if (animation != null) {
            animation.cancel()
            view.setAnimation(null)
        }
    }
}
