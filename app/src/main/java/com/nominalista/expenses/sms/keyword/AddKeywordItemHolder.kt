package com.nominalista.expenses.sms.keyword

import android.view.View

class AddKeywordItemHolder(itemView: View) : KeywordItemHolder(itemView) {
    fun bind(model: AddKeywordItemModel) {
        itemView.setOnClickListener { model.click?.invoke() }
    }

    fun recycle() {
        itemView.setOnClickListener(null)
    }
}
