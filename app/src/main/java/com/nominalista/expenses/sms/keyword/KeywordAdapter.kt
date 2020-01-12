package com.nominalista.expenses.sms.keyword

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nominalista.expenses.R

class KeywordAdapter : ListAdapter<KeywordItemModel, KeywordItemHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(viewType, parent, false)
        return when (viewType) {
            ADD_TAG_ITEM_TYPE -> AddKeywordItemHolder(
                    itemView
            )
            TAG_ITEM_TYPE -> KeywordItemHolderImpl(
                    itemView
            )
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: KeywordItemHolder, position: Int) {
        val itemModel = getItem(position)
        when {
            holder is AddKeywordItemHolder && itemModel is AddKeywordItemModel -> holder.bind(itemModel)
            holder is KeywordItemHolderImpl && itemModel is KeywordItemModelImpl -> holder.bind(itemModel)
        }
    }

    override fun onViewRecycled(holder: KeywordItemHolder) {
        super.onViewRecycled(holder)
        when (holder) {
            is AddKeywordItemHolder -> holder.recycle()
            is KeywordItemHolderImpl -> holder.recycle()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is AddKeywordItemModel -> ADD_TAG_ITEM_TYPE
            is KeywordItemModel -> TAG_ITEM_TYPE
            else -> throw IllegalArgumentException()
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<KeywordItemModel>() {

        override fun areItemsTheSame(
                oldItem: KeywordItemModel,
                newItem: KeywordItemModel
        ): Boolean {
            return when {
                oldItem is AddKeywordItemModel && newItem is AddKeywordItemModel -> true
                oldItem is KeywordItemModelImpl && newItem is KeywordItemModelImpl ->
                    oldItem.keyword.id == newItem.keyword.id
                else -> false
            }
        }

        override fun areContentsTheSame(
                oldItem: KeywordItemModel,
                newItem: KeywordItemModel
        ): Boolean {
            return when {
                oldItem is AddKeywordItemModel && newItem is AddKeywordItemModel -> true
                oldItem is KeywordItemModelImpl && newItem is KeywordItemModelImpl ->
                    oldItem.keyword == newItem.keyword
                else -> false
            }
        }
    }

    companion object {
        private const val ADD_TAG_ITEM_TYPE = R.layout.item_add_keyword
        private const val TAG_ITEM_TYPE = R.layout.item_keyword
    }
}