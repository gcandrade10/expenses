package com.nominalista.expenses.sms.keyword


import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.nominalista.expenses.R
import com.nominalista.expenses.data.model.Keyword
import com.nominalista.expenses.util.extensions.afterTextChanged
import com.nominalista.expenses.util.extensions.application
import com.nominalista.expenses.util.extensions.showKeyboard
import com.nominalista.expenses.util.extensions.toggleKeyboard

class AddKeywordFragment : Fragment() {
    private lateinit var saveButton: MenuItem
    private lateinit var model: KeywordFragmentModel

    private var id = ""

    private lateinit var keywordEditText: EditText
    private val keyword get() = keywordEditText.text.toString()

    private lateinit var firstSymbolEditText: EditText
    private val firstSymbol get() = firstSymbolEditText.text.toString()

    private lateinit var lastSymbolEditText: EditText
    private val lastSymbol get() = lastSymbolEditText.text.toString()

    private lateinit var decimalSeparatorEditText: EditText
    private val decimalSeparator get() = decimalSeparatorEditText.text.toString()

    private lateinit var groupSeparatorEditText: EditText
    private val groupSeparator get() = groupSeparatorEditText.text.toString()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_rule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupModels()
        setupActionBar()
        bindEditText(view)
        val myKeyword = arguments?.let { AddKeywordFragmentArgs.fromBundle(it).keyword }
        myKeyword?.id.let { id = it.orEmpty() }
        keywordEditText.setText(myKeyword?.name)
        firstSymbolEditText.setText(myKeyword?.firstSymbol)
        lastSymbolEditText.setText(myKeyword?.lastSymbol)
        decimalSeparatorEditText.setText(myKeyword?.decimalSeparator)
        groupSeparatorEditText.setText(myKeyword?.groupSeparator)
        watchEditText()
        showKeyboard(keywordEditText)
    }

    private fun setupModels() {
        val factory = KeywordFragmentModel.Factory(requireContext().application)
        model = ViewModelProviders.of(this, factory).get(KeywordFragmentModel::class.java)
    }

    private fun setupActionBar() {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar ?: return
        actionBar.title = ""
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeAsUpIndicator(R.drawable.ic_clear_24dp)
        setHasOptionsMenu(true)
    }

    private fun bindEditText(view: View) {
        keywordEditText = view.findViewById(R.id.keyword_edit_text)
        firstSymbolEditText = view.findViewById(R.id.first_symbol_edit_text)
        lastSymbolEditText = view.findViewById(R.id.last_symbol_edit_text)
        decimalSeparatorEditText = view.findViewById(R.id.decimal_separator_edit_text)
        groupSeparatorEditText = view.findViewById(R.id.group_separator_edit_text)
    }

    private fun watchEditText() {
        keywordEditText.afterTextChanged { enableOrDisableEditText() }
        firstSymbolEditText.afterTextChanged { enableOrDisableEditText() }
        lastSymbolEditText.afterTextChanged { enableOrDisableEditText() }
        decimalSeparatorEditText.afterTextChanged { enableOrDisableEditText() }
        groupSeparatorEditText.afterTextChanged { enableOrDisableEditText() }
    }

    private fun enableOrDisableEditText() {
        saveButton.isEnabled = keyword.isNotEmpty() && firstSymbol.isNotEmpty() && lastSymbol.isNotEmpty() && decimalSeparator.isNotEmpty() && groupSeparator.isNotEmpty()
    }

    // Options
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_new_expense, menu)
        saveButton = menu.getItem(0)
        enableOrDisableEditText()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> backSelected()
            R.id.save -> saveSelected()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun backSelected(): Boolean {
        toggleKeyboard()
        requireActivity().onBackPressed()
        return true
    }

    private fun saveSelected(): Boolean {
        save()
        return true
    }

    private fun save() {
        if (id.isNotEmpty()){
            model.updateKeyword(Keyword(id, keyword, firstSymbol, lastSymbol, decimalSeparator, groupSeparator))
        }
        else{
            model.createKeyword(Keyword(id, keyword, firstSymbol, lastSymbol, decimalSeparator, groupSeparator))
        }
        backSelected()
    }
}
