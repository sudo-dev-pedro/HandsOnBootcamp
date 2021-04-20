package br.com.raywenderlich.flighter.ui.boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.raywenderlich.flighter.R

class BoardingFragment : Fragment() {

    private lateinit var boardingViewModel: BoardingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        boardingViewModel =
            ViewModelProvider(this).get(BoardingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_boarding, container, false)
        val textView: TextView = root.findViewById(R.id.text_boarding_pass)
        boardingViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}