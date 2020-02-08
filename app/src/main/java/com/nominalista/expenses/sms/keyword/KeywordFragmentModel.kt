package com.nominalista.expenses.sms.keyword

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nominalista.expenses.Application
import com.nominalista.expenses.addeditexpense.presentation.tagselection.TagSelectionFragmentModel
import com.nominalista.expenses.data.model.Keyword
import com.nominalista.expenses.data.store.DataStore
import com.nominalista.expenses.util.extensions.TAG
import com.nominalista.expenses.util.extensions.plusAssign
import com.nominalista.expenses.util.reactive.DataEvent
import com.nominalista.expenses.util.reactive.Event
import com.nominalista.expenses.util.reactive.SchedulerTransformer
import com.nominalista.expenses.util.reactive.Variable
import io.reactivex.disposables.CompositeDisposable

class KeywordFragmentModel(private val dataStore: DataStore) : ViewModel() {

    val itemModels = Variable(emptyList<KeywordItemModel>())
    val showNewKeywordDialog = Event()

    val showEditKeyword = DataEvent<Keyword>()

    private val checkedKeywords: MutableSet<Keyword> = HashSet()
    private val disposables = CompositeDisposable()

    // Lifecycle start

    init {
        observeKeywords()
    }

    private fun observeKeywords() {
        disposables += dataStore.observeKeywords()
                .compose(SchedulerTransformer())
                .subscribe({ keywords ->
                    Log.d(TAG, "Keyword observation updated.")
                    updateItemModels(keywords)
                }, { error ->
                    Log.d(TAG, "Keyword observation failed (${error.message}).")
                })
    }

    private fun createAddKeywordSection() = listOf(createAddKeywordItemModel())

    private fun createAddKeywordItemModel(): AddKeywordItemModel {
        return AddKeywordItemModel().apply { click = { showNewKeywordDialog.next() } }
    }

    private fun createKeywordSection(keywords: List<Keyword>) = keywords.map { createKeywordItemModel(it) }

    private fun createKeywordItemModel(keyword: Keyword): KeywordItemModel {
        val itemModel = KeywordItemModelImpl(keyword)
        itemModel.editClick = { editKeyword(itemModel) }
        itemModel.deleteClick = { deleteKeyword(itemModel) }
        return itemModel
    }

    private fun editKeyword(itemModel: KeywordItemModelImpl) {
        showEditKeyword.next(itemModel.keyword)
    }

    private fun deleteKeyword(itemModel: KeywordItemModelImpl) {
        val keyword = itemModel.keyword

        checkedKeywords.remove(keyword)

        disposables += dataStore.deleteKeyword(keyword)
                .compose(SchedulerTransformer<Any>())
                .subscribe({
                    Log.d(TAG, "Keyword #${keyword.id} deletion succeeded.")
                }, { error ->
                    Log.d(TAG, "Keyword #${keyword.id} deletion failed (${error.message}..")
                })
    }

    private fun updateItemModels(tags: List<Keyword>) {
        itemModels.value = tags
                .sortedBy { it.name }
                .let { createAddKeywordSection() + createKeywordSection(it) }
    }

    // Lifecycle end

    override fun onCleared() {
        super.onCleared()
        clearDisposables()
    }

    private fun clearDisposables() {
        disposables.clear()
    }

    // Public

    fun createKeyword(keyword: Keyword) {
        disposables += dataStore.insertKeyword(keyword)
                .compose(SchedulerTransformer<Any>())
                .subscribe({
                    Log.d(TAG, "Keyword insertion succeeded.")
                }, { error ->
                    Log.d(TAG, "Keyword insertion failed (${error.localizedMessage}).")
                })
    }

    fun updateKeyword(keyword: Keyword) {
        disposables += dataStore.updateKeyword(keyword)
                .compose(SchedulerTransformer<Any>())
                .subscribe({
                    Log.d(TAG, "Keyword insertion succeeded.")
                }, { error ->
                    Log.d(TAG, "Keyword insertion failed (${error.localizedMessage}).")
                })
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return KeywordFragmentModel(application.defaultDataStore) as T
        }
    }
}