package com.nominalista.expenses.sms.keyword

import android.view.View
import androidx.appcompat.widget.PopupMenu
import com.nominalista.expenses.R
import kotlinx.android.synthetic.main.item_tag.view.*

class KeywordItemHolderImpl(itemView: View) : KeywordItemHolder(itemView) {
    fun bind(model: KeywordItemModelImpl) {
        itemView.text_name.text = model.name
        itemView.button_more.setOnClickListener { showMore(model) }
    }

    private fun showMore(model: KeywordItemModelImpl) {
        val context = itemView.context
        val popupMenu = PopupMenu(context, itemView.button_more)
        popupMenu.inflate(R.menu.rule_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete -> delete(model)
                else -> edit(model)
            }
        }
        popupMenu.show()
    }

    private fun edit(model: KeywordItemModelImpl): Boolean {
        model.editClick?.invoke()
        return true
    }

    fun delete(model: KeywordItemModelImpl): Boolean {
        model.deleteClick?.invoke()
        return true
    }

    fun recycle() {
        itemView.text_name.text = ""
        itemView.button_more.setOnClickListener(null)
    }
}
