package com.nominalista.expenses.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Keyword(val id: String, val name: String, val firstSymbol: String, val lastSymbol: String, val decimalSeparator: String, val groupSeparator: String) : Parcelable