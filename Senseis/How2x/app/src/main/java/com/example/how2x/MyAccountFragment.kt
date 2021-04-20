package com.example.how2x

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.how2x.App.Companion.sharedPreferences
import com.example.how2x.databinding.FragmentMyaccountBinding

class MyAccountFragment : Fragment(R.layout.fragment_myaccount) {

    private lateinit var binding: FragmentMyaccountBinding
    private lateinit var withdrawFragment: WithdrawFragment
    private lateinit var depositFragment: DepositFragment
    private lateinit var depositBundle: Bundle
    private lateinit var withdrawBundle: Bundle

    private var value: Int = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        depositBundle = Bundle()
        withdrawBundle = Bundle()
        withdrawFragment = WithdrawFragment()
        depositFragment = DepositFragment()

        binding = FragmentMyaccountBinding.inflate(inflater, container, false)
        binding.txtBalanceValue.text = value.toString()

        isSharedPreferencesSaved()
        getSharedPreferencesData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (sharedPreferences?.getInt(BALANCE_KEY, 500) == 500) {
            with(sharedPreferences?.edit()) {
                this?.putInt(BALANCE_KEY, 500)
                this?.apply()
            }
        }

        binding.txtBalanceValue.text = value.toString()

        onButtonApplyClicked()
        onButtonDepositClicked()
        onButtonWithdrawClicked()
        resetData()
    }

    private fun isSharedPreferencesSaved() {
        if (sharedPreferences != null) {
            getSharedPreferencesData()
            disableApplyNameSection()
            showPersonName()
            enableButtons()
        } else {
            enableApplyNameSection()
            onEditNameChanged()
        }
    }

    private fun getSharedPreferencesData() {
        value = sharedPreferences?.getInt(BALANCE_KEY, 500) ?: 0
        binding.txtPersonName.text = sharedPreferences?.getString(NAME_KEY, "")
    }

    private fun onEditNameChanged() {
        binding.edtName.doOnTextChanged { _, _, _, _ ->
            if (binding.edtName.text.isNotEmpty()) {
                enableButtons()
            }
        }
    }

    private fun showPersonName() {
        binding.txtPersonName.visibility = View.VISIBLE
    }

    private fun disableApplyNameSection() {
        binding.linearNameApply.visibility = View.INVISIBLE
    }

    private fun enableApplyNameSection() {
        binding.linearNameApply.visibility = View.VISIBLE
    }

    private fun enableButtons() {
        binding.btnWithDraw.isEnabled = true
        binding.btnDeposit.isEnabled = true
        binding.btnReset.isEnabled = true
    }

    private fun onButtonApplyClicked() {
        binding.btnApply.setOnClickListener {
            if (binding.edtName.text.isNotEmpty()) {
                binding.linearNameApply.visibility = View.INVISIBLE
                binding.txtPersonName.text = binding.edtName.text.toString()
                binding.txtPersonName.visibility = View.VISIBLE
                putPersonNameOnSharedPreferences()
            }
        }
    }

    private fun putPersonNameOnSharedPreferences() {
        with(sharedPreferences?.edit()) {
            this?.putString(NAME_KEY, binding.txtPersonName.text.toString())
            this?.apply()
        }
    }

    private fun onButtonDepositClicked() {
        binding.btnDeposit.setOnClickListener {
            callDepositFragment()
            with(sharedPreferences?.edit()) {
                this?.putString(NAME_KEY, binding.edtName.text.toString())
                this?.apply()
            }
        }
    }

    private fun onButtonWithdrawClicked() {
        binding.btnWithDraw.setOnClickListener {
            callWithdrawFragment()
            with(sharedPreferences?.edit()) {
                this?.putString(NAME_KEY, binding.txtPersonName.text.toString())
                this?.apply()
            }
        }
    }

    private fun callDepositFragment() {
        prepareTheBundleForDeposit()
        requireFragmentManager().beginTransaction()
            .replace(R.id.ConstraintLayout, depositFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun prepareTheBundleForDeposit() {
        depositBundle.putString("PERSON_NAME", binding.edtName.text.toString())
        depositFragment.arguments = depositBundle
    }

    private fun callWithdrawFragment() {
        prepareTheBundleForWithdraw()
        requireFragmentManager().beginTransaction()
            .replace(R.id.ConstraintLayout, withdrawFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun prepareTheBundleForWithdraw() {
        withdrawBundle.putString("PERSON_NAME", binding.txtPersonName.text.toString())
        withdrawFragment.arguments = withdrawBundle
    }

    private fun resetData() {
        binding.btnReset.setOnClickListener {
            binding.edtName.text = null
            binding.txtPersonName.visibility = View.INVISIBLE
            binding.linearNameApply.visibility = View.VISIBLE
            binding.txtBalanceValue.text = value.toString()
            sharedPreferences?.edit()?.clear()?.apply()
            depositBundle.remove(NAME_KEY)
        }
    }

    companion object {
        const val NAME_KEY = "NAME_KEY"
        const val BALANCE_KEY = "BALANCE_KEY"
    }
}