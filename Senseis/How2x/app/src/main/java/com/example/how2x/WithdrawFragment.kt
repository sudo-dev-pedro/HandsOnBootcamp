package com.example.how2x

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.how2x.databinding.FragmentWithdrawBinding

class WithdrawFragment : Fragment() {

    private lateinit var binding: FragmentWithdrawBinding
    private var username: String? = ""

    //Isso aqui pode ser mais genérico e colocado em um escopo mais global!
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
        binding = FragmentWithdrawBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = this.activity?.getPreferences(Context.MODE_PRIVATE)

        binding.personName.text = username

        binding.btnDeposit.setOnClickListener {
            var withdrawVal = binding.depositValue.text.toString().toInt()

            withdrawVal = sharedPreferences?.getInt("Saldo", 500) ?: 0 - withdrawVal

            with(sharedPreferences?.edit()) {
                this?.putInt("SALDO", withdrawVal)
                this?.apply()
            }

            binding.returnMyAcc.visibility = View.VISIBLE
        }

        binding.returnMyAcc.setOnClickListener {
            requireFragmentManager().beginTransaction()
                .replace(R.id.ConstraintLayout, MyAccountFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}