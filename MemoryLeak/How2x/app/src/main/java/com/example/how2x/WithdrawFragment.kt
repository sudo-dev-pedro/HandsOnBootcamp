package com.example.how2x

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.how2x.databinding.WithdrawFragmentBinding

class WithdrawFragment : Fragment() {

    private var binding: WithdrawFragmentBinding? = null
    private var username: String? = ""
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        username = arguments?.getString("PERSON_NAME")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WithdrawFragmentBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = this.activity?.getPreferences(Context.MODE_PRIVATE)

        binding?.personName?.text = username
        Log.d("NOME", binding?.personName?.text.toString())

        binding?.btnDeposit?.setOnClickListener {
            var withdrawVal = binding?.depositValue?.text.toString().toInt()

            withdrawVal = sharedPreferences?.getInt("Saldo", 500)!! - withdrawVal

            with(sharedPreferences?.edit()) {
                this?.putInt("Saldo", withdrawVal)
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}