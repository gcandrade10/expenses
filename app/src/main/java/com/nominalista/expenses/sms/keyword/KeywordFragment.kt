package com.nominalista.expenses.sms.keyword


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nominalista.expenses.R
import com.nominalista.expenses.data.model.Keyword
import com.nominalista.expenses.util.extensions.application
import com.nominalista.expenses.util.extensions.plusAssign
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_keyword.*

class KeywordFragment : Fragment() {

    private lateinit var adapter: KeywordAdapter
    private lateinit var model: KeywordFragmentModel

    private val disposables = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_keyword, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()
        setupRecyclerView()
        setupModels()
        bindModel()
    }

    private fun setupActionBar() {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar ?: return
        actionBar.title = getString(R.string.rules)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp)
        setHasOptionsMenu(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> backSelected()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecyclerView() {
        adapter = KeywordAdapter()
        keywordRecyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext())
        keywordRecyclerView.layoutManager = layoutManager
    }

    private fun setupModels() {
        val factory = KeywordFragmentModel.Factory(requireContext().application)
        model = ViewModelProviders.of(this, factory).get(KeywordFragmentModel::class.java)
    }

    private fun bindModel() {
        disposables += model.itemModels.toObservable().subscribe(adapter::submitList)
        disposables += model.showNewKeywordDialog.toObservable().subscribe { showEditKeyword(null) }
        disposables += model.showEditKeyword.toObservable().subscribe { showEditKeyword(it) }
    }

    private fun showEditKeyword(keyword: Keyword?) {
        val action = KeywordFragmentDirections.actionKeywordFragment2ToAddKeywordFragment()
        action.keyword = keyword
        val navController = NavHostFragment.findNavController(this)
        if (navController.currentDestination?.id == R.id.keywordFragment2) {
            navController.navigate(action)
        }

    }

    // Options

    private fun backSelected(): Boolean {
        requireActivity().setResult(Activity.RESULT_OK)
        requireActivity().finish()
        return true
    }


}
