package com.example.how2x

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.how2x.databinding.DepositFragmentBinding

class DepositFragment : Fragment(R.layout.deposit_fragment) {
    private var binding: DepositFragmentBinding? = null
    private var username: String? = ""
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DepositFragmentBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = this.activity?.getPreferences(Context.MODE_PRIVATE)

        binding?.personName?.text = username

        binding?.btnDeposit?.setOnClickListener {
            var depositaVal = binding?.depositValue?.text.toString().toInt()

            depositaVal += sharedPreferences?.getInt("Saldo", 500) ?: 500
            with(sharedPreferences?.edit()) {
                this?.putInt("Saldo", depositaVal)
                this?.apply()
            }

            binding?.returnMyAcc?.visibility = View.VISIBLE
        }

        binding?.returnMyAcc?.setOnClickListener {
            requireFragmentManager().beginTransaction()
                .replace(R.id.ConstraintLayout, MyAccountFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        username = arguments?.getString("PERSON_NAME")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}