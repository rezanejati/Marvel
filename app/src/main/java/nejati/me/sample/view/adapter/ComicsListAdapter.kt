package nejati.me.sample.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import nejati.me.sample.base.BaseAdapter
import nejati.me.sample.base.BaseViewHolder
import nejati.me.sample.databinding.ComicsListItemBinding
import nejati.me.sample.viewModel.comicsList.ComicsItemViewModel
import nejati.me.sample.viewModel.comicsList.ComicsViewModel
import nejati.me.service.model.comics.response.Data
import nejati.me.service.model.comics.response.Result

/**
 * Authors:
 * Reza Nejati <rn.nejati@gmail.com>
 * Copyright © 2019
 */
class ComicsListAdapter(
    private val comicsItems: MutableList<Result>,
    comicsViewModel: ComicsViewModel):
    BaseAdapter<BaseViewHolder, Data>() {

    var comicsViewModel: ComicsViewModel

    init {
       this.comicsViewModel=comicsViewModel
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder {
        val adapterBinding = ComicsListItemBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup, false)

        return ComicsListViewHolder(adapterBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return comicsItems.size
    }

    inner class ComicsListViewHolder(private val adapterBinding: ComicsListItemBinding) :
        BaseViewHolder(adapterBinding.root), CustomClickListener {

        private var comicsItemViewModel: ComicsItemViewModel? = null

        override fun onBind(position: Int) {
            if (comicsItems.size > 0) {
                val comicsListItem = comicsItems[position]
                comicsItemViewModel = ComicsItemViewModel(comicsListItem, this)
                adapterBinding.viewModel = comicsItemViewModel

            }
        }


        override fun itemClicked(f: ComicsItemViewModel) {
            comicsViewModel.onComicsItemClick(adapterPosition)
        }
    }
}
