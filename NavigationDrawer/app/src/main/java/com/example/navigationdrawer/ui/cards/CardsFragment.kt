package com.example.navigationdrawer.ui.cards

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.navigationdrawer.databinding.FragmentCardsBinding

class CardsFragment : Fragment() {

    private lateinit var cardsViewModel: CardsViewModel
    private var cardsBinding: FragmentCardsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cardsViewModel =
            ViewModelProvider(this).get(CardsViewModel::class.java)

        cardsBinding = FragmentCardsBinding.inflate(layoutInflater, container, false)

        return cardsBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardsViewModel.text.observe(viewLifecycleOwner) {
            cardsBinding?.textAbout?.text = it
        }

        initPhoneTextWatcher()
    }

    private fun initPhoneTextWatcher() {
        cardsBinding?.etPhoneNumber?.addTextChangedListener(object : TextWatcher {
//            var lastChar: String = ""

            override fun beforeTextChanged(
                text: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
//                val digits: Int = cardsBinding?.etPhoneNumber?.text.toString().length
//
//                if (digits > 1) {
//                    lastChar = cardsBinding?.etPhoneNumber?.text.toString().substring(digits - 1)
//                }
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                when (cardsBinding?.etPhoneNumber?.text.toString().length) {
                    1 -> cardsBinding?.etPhoneNumber?.setText(
                        StringBuilder(text.toString())
                            .insert(text.toString().length - 1, "(")
                            .toString()
                    )
                    3 -> cardsBinding?.etPhoneNumber?.setText(
                        StringBuilder(text.toString())
                            .insert(text.toString().length - 1, ")")
                            .toString()
                    )
                }
            }

//            https://gist.github.com/alfredbaudisch/f4416061dd1858b1f4ee
//            https://gist.github.com/kvdesa/113ef4ababc1aab19e55551b91aa9f37

            override fun afterTextChanged(text: Editable?) {
//                when (cardsBinding?.etPhoneNumber?.text.toString().length) {
//                    0 -> text?.clear()
//                }

//                if (text != null) {
//                    if (text.contains("(")) {
//                        text.clear()
//                    }
//                }
            }

        })
    }

}