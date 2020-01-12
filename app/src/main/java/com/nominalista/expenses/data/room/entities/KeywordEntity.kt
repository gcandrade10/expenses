package com.nominalista.expenses.data.room.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nominalista.expenses.data.model.Keyword
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "keywords")
data class KeywordEntity(
        @PrimaryKey(autoGenerate = true) val id: Long,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "firstSymbol") val firstSymbol: String,
        @ColumnInfo(name = "lastSymbol") val lastSymbol: String,
        @ColumnInfo(name = "decimalSeparator") val decimalSeparator: String,
        @ColumnInfo(name = "groupSeparator") val groupSeparator: String
) : Parcelable {

    fun mapToKeyword() = Keyword(id = id.toString(), name = name, firstSymbol = firstSymbol, lastSymbol = lastSymbol, decimalSeparator = decimalSeparator, groupSeparator = groupSeparator)

    companion object {



        fun prepareForInsertion(keyword: Keyword) = KeywordEntity(id = 0, name = keyword.name, firstSymbol = keyword.firstSymbol, lastSymbol = keyword.lastSymbol, decimalSeparator = keyword.decimalSeparator, groupSeparator = keyword.groupSeparator)

        fun fromKeyword(keyword: Keyword) = KeywordEntity(id = keyword.id.toLong(), name = keyword.name, firstSymbol = keyword.firstSymbol, lastSymbol = keyword.lastSymbol, decimalSeparator = keyword.decimalSeparator, groupSeparator = keyword.groupSeparator)
    }
}