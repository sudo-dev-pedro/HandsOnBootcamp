package com.example.how2x

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.how2x.databinding.MyaccountFragmentBinding

class MyAccountFragment : Fragment(R.layout.myaccount_fragment) {

    private var binding: MyaccountFragmentBinding? = null
    private var value: Int = 500
    private var sharedPreferences: SharedPreferences? = null

    //private val mPreferences by lazy { activity?.getPreferences(Context.MODE_PRIVATE) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MyaccountFragmentBinding.inflate(inflater, container, false)

        disableButtons()

        binding?.value?.text = value.toString()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()

        sharedPreferences = this.activity?.getPreferences(Context.MODE_PRIVATE)

        value = sharedPreferences?.getInt("Saldo", 500) ?: 500
        binding?.showName?.text = sharedPreferences?.getString("NAME", "")

        if (sharedPreferences?.getInt("Saldo", 500) == 500) {
            with(sharedPreferences?.edit()) {
                this?.putInt("Saldo", 500)
                this?.apply()
            }
        }

        binding?.value?.text = value.toString()

        binding?.apply?.setOnClickListener {
            if (binding?.name?.text?.isNotEmpty() == true) {
                binding?.showName?.text = binding?.name?.text
                binding?.LinearNameApply?.visibility = View.GONE
                binding?.showName?.visibility = View.VISIBLE
            }
        }

        binding?.showName?.doOnTextChanged { _, _, _, _ ->
            if (binding?.showName?.text?.isNotEmpty() == true) {
                binding?.withdraw?.isEnabled = true
                binding?.deposit?.isEnabled = true
                binding?.reset?.isEnabled = true
            } else {
                disableButtons()
            }
        }

        binding?.reset?.setOnClickListener {
            binding?.showName?.text = ""
            binding?.showName?.visibility = View.INVISIBLE
            binding?.LinearNameApply?.visibility = View.VISIBLE
            binding?.value?.text = value.toString()
            sharedPreferences?.edit()
                ?.clear()
                ?.apply()
            bundle.remove("PERSON_NAME")
        }

        binding?.deposit?.setOnClickListener {
            bundle.putString("PERSON_NAME", binding?.showName?.text.toString())
            with(sharedPreferences?.edit()) {
                this?.putString("NAME", binding?.showName?.text.toString())
                this?.apply()
            }
            requireFragmentManager().beginTransaction()
                .replace(R.id.ConstraintLayout, DepositFragment().apply {
                    arguments = bundle
                })
                .addToBackStack(null)
                .commit()
        }

        binding?.withdraw?.setOnClickListener {
            bundle.putString("PERSON_NAME", binding?.showName?.text.toString())
            with(sharedPreferences?.edit()) {
                this?.putString("NAME", binding?.showName?.text.toString())
                this?.apply()
            }
            requireFragmentManager().beginTransaction()
                .replace(R.id.ConstraintLayout, WithdrawFragment().apply {
                    arguments = bundle
                })
                .addToBackStack(null)
                .commit()
        }
    }

    private fun disableButtons() {
        binding?.withdraw?.isEnabled = false
        binding?.deposit?.isEnabled = false
        binding?.reset?.isEnabled = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val NAME_KEY = "NAME"
    }
}