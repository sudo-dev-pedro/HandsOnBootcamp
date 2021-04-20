package com.example.banksystem.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.banksystem.R
import com.example.banksystem.domain.Account
import com.example.banksystem.domain.User
import com.example.banksystem.extensions.toFloat
import com.example.banksystem.usecase.ValidateAccountUseCase
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    // Text view to display both count and color
    private lateinit var mResultTextView: TextView
    private lateinit var mAccountBalanceTextView: TextView
    private lateinit var mWithdrawValueInputText: TextInputEditText
    private lateinit var mTransferToInputText: TextInputEditText
    private lateinit var mTransferValueInputText: TextInputEditText
    private lateinit var mCpfInputText: TextInputEditText
    private lateinit var _accounts: List<Account>

    private val validateAccountUseCase = ValidateAccountUseCase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize
        mWithdrawValueInputText = findViewById(R.id.withdrawValue_inputText)
        mTransferToInputText = findViewById(R.id.transferTo_inputText)
        mTransferValueInputText = findViewById(R.id.transferValue_inputText)
        mCpfInputText = findViewById(R.id.cpf_inputText)

        mResultTextView = findViewById(R.id.result_textview)
        mResultTextView.text = getString(R.string.system_idle)

        mAccountBalanceTextView = findViewById(R.id.accountBalance_textview)
        mAccountBalanceTextView.text = "0"

        _accounts = listOf(
            Account(
                User(
                    "111",
                    "Pedro",
                    "pedro@mail.com"
                ), 10000f, "024-2", "001", 13
            ),

            Account(
                User(
                    "222",
                    "Henrique",
                    "henrique@mail.com"
                ),
                20000f, "025-1", "002", 24
            ),
        )
    }

    /**
     * Handles the onClick for the withdraw button.
     *
     * @param view
     */
    fun withdraw(view: View) {
        val currentAccount =
            validateAccountUseCase.accountExists(_accounts, mCpfInputText.text.toString())

        if (currentAccount == null) {
            mResultTextView.text = getString(R.string.no_account_found)
            return
        }

        val currentBalance = currentAccount.balance
        val withdrawValue = mWithdrawValueInputText.text.toFloat()

        if (!validateAccountUseCase.checkBalanceForTransaction(currentAccount, withdrawValue)) {
            mResultTextView.text = getString(R.string.insufficient_balance)
            return
        }

        mResultTextView.text = "Withdraw amount: ${mWithdrawValueInputText.text}"

        currentAccount.balance =
            currentBalance.minus(mWithdrawValueInputText.text.toFloat())

        mAccountBalanceTextView.text = "New balance ${currentAccount.balance}"

        clear()
    }

    /**
     * Handles the onClick for the transfer button.
     *
     * @param view
     */
    fun transfer(view: View?) {
        val currentAccount =
            validateAccountUseCase.accountExists(_accounts, mCpfInputText.text.toString())

        if (currentAccount == null) {
            mResultTextView.text = getString(R.string.no_account_found)
            return
        }

        val transferValue = mTransferValueInputText.text.toFloat()

        if (!validateAccountUseCase.checkBalanceForTransaction(currentAccount, transferValue)) {
            mResultTextView.text = getString(R.string.insufficient_balance)
            return
        }

        val destinationAccount =
            validateAccountUseCase.accountExists(_accounts, mTransferToInputText.text.toString())

        if (destinationAccount == null) {
            mResultTextView.text = getString(R.string.destination_account_not_found)
            return
        }

        mResultTextView.text =
            "Transferred amount: ${mTransferValueInputText.text} to: ${destinationAccount.user.name}"

        currentAccount.transferTo(destinationAccount, mTransferValueInputText.text.toFloat())

        mAccountBalanceTextView.text = getString(R.string.current_balance, currentAccount.balance)

        clear()
    }

    /**
     * Handles the onClick for the balance button.
     *
     * @param view
     */
    fun balance(view: View?) {
        val currentAccount =
            validateAccountUseCase.accountExists(_accounts, mCpfInputText.text.toString())

        if (currentAccount == null) {
            mResultTextView.text = getString(R.string.no_account_found)
            return
        }

        mAccountBalanceTextView.text = getString(R.string.current_balance, currentAccount.balance)
    }

    /**
     * Handles the onClick for the clear button.
     *
     * @param view
     */
    fun clear(view: View? = null) {
        mWithdrawValueInputText.text?.clear()
        mTransferValueInputText.text?.clear()
        mTransferToInputText.text?.clear()

        clearResult()
    }

    private fun clearResult() {
        mResultTextView.text = ""
        mAccountBalanceTextView.text = ""
    }
}