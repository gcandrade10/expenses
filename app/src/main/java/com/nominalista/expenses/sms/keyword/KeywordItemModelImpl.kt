package com.nominalista.expenses.sms.keyword

import com.nominalista.expenses.data.model.Keyword

class KeywordItemModelImpl(val keyword: Keyword) : KeywordItemModel {
    var isChecked = false
    val name = keyword.name

    var editClick: (() -> Unit)? = null
    var deleteClick: (() -> Unit)? = null
}
