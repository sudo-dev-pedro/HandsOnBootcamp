package com.example.navigationdrawer.ui.cards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.navigationdrawer.R
import com.example.navigationdrawer.ui.about.AboutViewModel

class CardsFragment : Fragment() {

    private lateinit var cardsViewModel: CardsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cardsViewModel =
            ViewModelProvider(this).get(CardsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_cards, container, false)
        val textView: TextView = root.findViewById(R.id.text_about)

        cardsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

}