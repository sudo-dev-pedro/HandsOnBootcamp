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
        mResultTextView.text = "System is idle."
        mAccountBalanceTextView = findViewById(R.id.accountBalance_textview)
        mAccountBalanceTextView.text = "0"

        _accounts = listOf(
            Account(User("Thiago Bartoleti", "11111111111"), 10000f),
            Account(User("Sueli Macedo", "22222222222"), 100.5f),
            Account(User("Vitor Silva", "33333333333"), -10f),
            Account(User("Mariana Doro", "44444444444"), 0f)
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
            mResultTextView.text = "No account found"
            return
        }

        val currentBalance = currentAccount.balance ?: 0f
        val withdrawValue = mWithdrawValueInputText.text.toFloat()
        if (!validateAccountUseCase.checkBalanceForTransaction(currentAccount, withdrawValue)) {
            mResultTextView.text = "Insufficient balance for this operation"
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
            mResultTextView.text = "No account found"
            return
        }

        val transferValue = mTransferValueInputText.text.toFloat()
        if (!validateAccountUseCase.checkBalanceForTransaction(currentAccount, transferValue)) {
            mResultTextView.text = "Insufficient balance for this operation"
            return
        }

        val destinationAccount =
            validateAccountUseCase.accountExists(_accounts, mTransferToInputText.text.toString())
        if (destinationAccount == null) {
            mResultTextView.text = "Destination account not found"
            return
        }

        mResultTextView.text =
            "Transferred amount: ${mTransferValueInputText.text} to: ${destinationAccount.owner.name}"

        currentAccount.transferTo(destinationAccount, mTransferValueInputText.text.toFloat())

        mAccountBalanceTextView.text = "Current balance ${currentAccount.balance}"

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
            mResultTextView.text = "No account found"
            return
        }

        mAccountBalanceTextView.text = "Current balance ${currentAccount.balance}"
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

    fun clearResult() {
        mResultTextView.text = ""
        mAccountBalanceTextView.text = ""
    }
}