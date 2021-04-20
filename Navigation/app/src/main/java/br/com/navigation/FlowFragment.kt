package br.com.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import java.lang.NullPointerException

// Chame no seu nav_graph
class FlowFragment : Fragment() {

    private lateinit var number: Any

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        number = arguments?.get("number")!!

        // Inflar de acordo com o numero que é coletado no argumento
        return when (number) {
            1 -> inflater.inflate(R.layout.fragment_one, container, false)
            2 -> inflater.inflate(R.layout.fragment_two, container, false)
            3 -> inflater.inflate(R.layout.fragment_three, container, false)
            else -> throw NullPointerException()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnNextFragment).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_next_fragment)
        )
    }
}