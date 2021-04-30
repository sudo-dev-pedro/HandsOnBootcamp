package com.example.navigationdrawer.ui.statement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.navigationdrawer.R
import com.example.navigationdrawer.ui.schedule.ScheduleViewModel

class StatementFragment : Fragment() {

    private lateinit var statementViewModel: StatementViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        statementViewModel =
            ViewModelProvider(this).get(StatementViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_statement, container, false)
        val textView: TextView = root.findViewById(R.id.text_statement)

        statementViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }
}