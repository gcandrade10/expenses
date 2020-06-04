package com.nominalista.expenses.addeditexpense.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.util.Log
import android.view.*
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.chip.Chip
import com.nominalista.expenses.R
import com.nominalista.expenses.addeditexpense.presentation.dateselection.DateSelectionDialogFragment
import com.nominalista.expenses.currencyselection.CurrencySelectionActivity
import com.nominalista.expenses.data.model.Currency
import com.nominalista.expenses.data.model.Tag
import com.nominalista.expenses.util.READABLE_DATE_FORMAT
import com.nominalista.expenses.util.extensions.*
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_add_edit_expense.*
import org.threeten.bp.LocalDate
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.ParseException
import java.util.*

class AddEditExpenseFragment : Fragment() {

    private lateinit var activityModel: AddEditExpenseActivityModel
    private lateinit var model: AddEditExpenseFragmentModel

    private val disposables = CompositeDisposable()

    // Lifecycle start

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add_edit_expense, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()
        addListeners()
        initializeModels()
        bindModels()
        showKeyboard()
        watchEditTexts()
    }

    private fun setupActionBar() {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar ?: return
        actionBar.title = ""
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeAsUpIndicator(R.drawable.ic_clear_24dp)
        setHasOptionsMenu(true)
    }

    private fun watchEditTexts() {
        model.selectedCurrency.value.locale?.let {
            editTextAmount.addTextChangedListener(NumberTextWatcher(editTextAmount, it, model.selectedCurrency.value.defaultDecimals))
        }
        editTextAmount.afterTextChanged { text ->
            var amount = text.toString()
            model.selectedCurrency.value.locale?.let { locale ->
                val symbols = DecimalFormatSymbols(locale)
                val gs = symbols.groupingSeparator
                val ds = symbols.decimalSeparator
                val nAmount = text.toString().replace("$gs", "")
                nAmount.let {
                    amount = it.replace("$ds", ".")
                }
            }
            model.updateAmount(amount.toDoubleOrNull() ?: 0.0)
        }
        editTextTitle.afterTextChanged { model.updateTitle(it.toString()) }
        editTextNotes.afterTextChanged { model.updateNotes(it.toString()) }
    }

    private fun addListeners() {
        textSymbol.setOnClickListener { showCurrencySelection() }
        containerTags.setOnClickListener { showTagSelection() }
        textDate.setOnClickListener { showDateSelection() }
    }

    private fun showCurrencySelection() {
        CurrencySelectionActivity.start(this, REQUEST_CODE_SELECT_CURRENCY)
    }

    private fun showTagSelection() {
        NavHostFragment.findNavController(this).navigate(R.id.action_tag_selection)
    }

    private fun showDateSelection() {
        DateSelectionDialogFragment.newInstance().apply {
            dateSelected = { y, m, d -> model.selectDate(y, m, d) }
        }.show(requireFragmentManager(), DateSelectionDialogFragment.TAG)
    }

    private fun initializeModels() {
        activityModel = ViewModelProviders.of(requireActivity())
            .get(AddEditExpenseActivityModel::class.java)

        val args = arguments?.let { AddEditExpenseFragmentArgs.fromBundle(it) }
        val factory =
            AddEditExpenseFragmentModel.Factory(requireContext().application, args?.expense)
        model = ViewModelProviders.of(this, factory).get(AddEditExpenseFragmentModel::class.java)
    }

    private fun bindModels() {
        activityModel.selectedTags.observe(this, Observer { model.selectTags(it) })

        disposables += model.selectedCurrency
            .toObservable()
            .subscribe { updateCurrencyText(it) }
        disposables += model.selectedDate.toObservable().subscribe { updateDateText(it) }
        disposables += model.selectedTags.toObservable().subscribe { updateTagLayout(it) }
        disposables += model.finish.toObservable().subscribe { finish() }

        editTextAmount.setText(makeEasilyEditableAmount(model.amount))
        editTextTitle.setText(model.title)
        editTextNotes.setText(model.notes)
    }

    private fun makeEasilyEditableAmount(amount: Double?): String {
        return when {
            amount == null -> ""
            amount - amount.toInt().toDouble() == 0.0 -> amount.toInt().toString()
            else -> amount.toString()
        }
    }

    private fun updateCurrencyText(currency: Currency) {
        val context = requireContext()
        val text = context.getString(
            R.string.currency_abbreviation,
            currency.flag,
            currency.code
        )
        textSymbol.text = text
    }

    private fun updateDateText(date: LocalDate) {
        textDate.text = date.toString(READABLE_DATE_FORMAT)
    }

    private fun updateTagLayout(tags: List<Tag>) {
        updateSelectTagsText(tags.isEmpty())
        updateChipGroup(tags)
    }

    private fun updateSelectTagsText(isVisible: Boolean) {
        text_select_tags.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun updateChipGroup(tags: List<Tag>) {
        chip_group.removeAllViews()
        tags.forEach { chip_group.addView(createChip(it.name)) }
    }

    private fun createChip(text: String): Chip {
        val chip = Chip(context)
        chip.text = text
        chip.isClickable = false
        return chip
    }

    private fun finish() {
        requireActivity().onBackPressed()
    }

    private fun showKeyboard() {
        showKeyboard(editTextAmount, KEYBOARD_APPEARANCE_DELAY)
    }

    // Lifecycle end

    override fun onDestroyView() {
        super.onDestroyView()
        clearDisposables()
        hideKeyboard()
    }

    private fun clearDisposables() {
        disposables.clear()
    }

    // Options

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_new_expense, menu)
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
        requireActivity().onBackPressed()
        return true
    }

    private fun saveSelected(): Boolean {
        model.saveExpense()
        return true
    }

    // Results

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) return

        when(requestCode) {
            REQUEST_CODE_SELECT_CURRENCY -> {
                val currency: Currency? =
                    data?.getParcelableExtra(CurrencySelectionActivity.EXTRA_CURRENCY)
                currency?.let { model.selectCurrency(it) }
            }
        }
    }

    companion object {

        private const val REQUEST_CODE_SELECT_CURRENCY = 1

        private const val KEYBOARD_APPEARANCE_DELAY = 300L
    }
}


class NumberTextWatcher(et: EditText, locale: Locale?, numDecimals: Int) : TextWatcher {
    private val numDecimals: Int
    private val groupingSep: String
    private val decimalSep: String
    private val nonUsFormat: Boolean
    private val df: DecimalFormat
    private val dfnd: DecimalFormat
    private var hasFractionalPart: Boolean
    private val et: EditText
    private var value: String?
    private fun replicate(ch: Char, n: Int): String {
        return String(CharArray(n)).replace("\u0000", "" + ch)
    }

    override fun afterTextChanged(s: Editable) {
        Log.d(TAG, "afterTextChanged")
        et.removeTextChangedListener(this)
        try {
            val inilen: Int
            val endlen: Int
            inilen = et.text.length
            var v = value!!.replace(groupingSep, "")
            val n = df.parse(v)
            val cp = et.selectionStart
            if (hasFractionalPart) {
                val decPos = v.indexOf(decimalSep) + 1
                val decLen = v.length - decPos
                if (decLen > numDecimals) {
                    v = v.substring(0, decPos + numDecimals)
                }
                var trz = countTrailingZeros(v)
                val fmt = StringBuilder(df.format(n))
                while (trz-- > 0) {
                    fmt.append("0")
                }
                et.setText(fmt.toString())
            } else {
                et.setText(dfnd.format(n))
            }
            endlen = et.text.length
            val sel = cp + (endlen - inilen)
            if (sel > 0 && sel <= et.text.length) {
                et.setSelection(sel)
            } else {
                // place cursor at the end?
                et.setSelection(et.text.length - 1)
            }
        } catch (nfe: NumberFormatException) {
            // do nothing?
        } catch (nfe: ParseException) {
        }
        et.addTextChangedListener(this)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        Log.d(TAG, "beforeTextChanged")
        value = et.text.toString()
    }

    private fun countTrailingZeros(str: String): Int {
        var count = 0
        for (i in str.length - 1 downTo 0) {
            val ch = str[i]
            if ('0' == ch) {
                count++
            } else {
                break
            }
        }
        return count
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        Log.d(TAG, "onTextChanged")
        val newValue = s.toString()
        var change = newValue.substring(start, start + count)
        val prefix = value!!.substring(0, start)
        val suffix = value!!.substring(start + before)
        if ("." == change && nonUsFormat) {
            change = decimalSep
        }
        value = prefix + change + suffix
        hasFractionalPart = value!!.contains(decimalSep)
        Log.d(TAG, "VALUE: $value")
    }

    companion object {
        private const val TAG = "NumberTextWatcher"
    }

    init {
        et.keyListener = DigitsKeyListener.getInstance("0123456789.,")
        this.numDecimals = numDecimals
        val symbols = DecimalFormatSymbols(locale)
        val gs = symbols.groupingSeparator
        val ds = symbols.decimalSeparator
        groupingSep = gs.toString()
        decimalSep = ds.toString()
        val patternInt = "#,###"
        dfnd = DecimalFormat(patternInt, symbols)
        val patternDec = patternInt + "." + replicate('#', numDecimals)
        df = DecimalFormat(patternDec, symbols)
        df.isDecimalSeparatorAlwaysShown = true
        df.roundingMode = RoundingMode.DOWN
        this.et = et
        hasFractionalPart = false
        nonUsFormat = decimalSep != "."
        value = null
    }
}