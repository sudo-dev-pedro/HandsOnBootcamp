package com.example.how2x

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.how2x.App.Companion.sharedPreferences
import com.example.how2x.MyAccountFragment.Companion.BALANCE_KEY
import com.example.how2x.MyAccountFragment.Companion.NAME_KEY
import com.example.how2x.databinding.FragmentDepositBinding

class DepositFragment : Fragment(R.layout.fragment_deposit) {

    private lateinit var binding: FragmentDepositBinding
    private var username: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        username = arguments?.getString(NAME_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDepositBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = this.activity?.getPreferences(Context.MODE_PRIVATE)

        binding.personName.text = username

        binding.btnDeposit.setOnClickListener {
            var depositValue = binding.depositValue.text.toString().toInt()

            depositValue += sharedPreferences?.getInt(BALANCE_KEY, 500) ?: 0

            with(sharedPreferences?.edit()) {
                this?.putInt(BALANCE_KEY, depositValue)
                this?.apply()
            }

            binding.btnReturnToAccount.visibility = View.VISIBLE
        }

        binding.btnReturnToAccount.setOnClickListener {
            requireFragmentManager().beginTransaction()
                .replace(R.id.ConstraintLayout, MyAccountFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}