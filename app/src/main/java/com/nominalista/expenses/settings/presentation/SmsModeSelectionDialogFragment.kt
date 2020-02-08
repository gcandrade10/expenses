package com.nominalista.expenses.settings.presentation

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nominalista.expenses.R
import com.nominalista.expenses.common.presentation.SmsMode

class SmsModeSelectionDialogFragment : DialogFragment(), DialogInterface.OnClickListener {

    var smsModeSelected: ((SmsMode) -> Unit)? = null

    private val themes by lazy { SmsMode.values() }

    private lateinit var currentDarkMode: SmsMode

    private var selectedSmsMode: SmsMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentDarkMode = arguments?.getString(ARGUMENT_CURRENT_SMS_MODE)
            ?.let { SmsMode.valueOf(it) } ?: SmsMode.OFF
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireActivity())
            .setTitle(R.string.sms_reader)
            .setSingleChoiceItems(getItems(), getCheckedItem(), this)
            .setPositiveButton(R.string.ok) { _, _ ->
                selectedSmsMode?.let { smsModeSelected?.invoke(it) }
            }
            .setNegativeButton(R.string.cancel) { _, _ -> }
            .create()
    }

    private fun getItems(): Array<String> {
        return themes.map { theme ->
            when (theme) {
                SmsMode.ON -> requireContext().getString(R.string.on)
                SmsMode.OFF -> requireContext().getString(R.string.off)
            }
        }.toTypedArray()
    }

    private fun getCheckedItem(): Int = themes.indexOf(currentDarkMode)

    override fun onClick(dialog: DialogInterface, which: Int) {
        selectedSmsMode = themes[which]
    }

    companion object {
        private const val ARGUMENT_CURRENT_SMS_MODE = "com.nominalista.expenses.ARGUMENT_CURRENT_SMS_MODE"

        fun newInstance(currentSmsMode: SmsMode): SmsModeSelectionDialogFragment {
            val arguments = Bundle().apply {
                putString(ARGUMENT_CURRENT_SMS_MODE, currentSmsMode.name)
            }

            return SmsModeSelectionDialogFragment().apply { this.arguments = arguments }
        }
    }
}