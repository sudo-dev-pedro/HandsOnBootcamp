package com.androidbootcamp.kotlincoroutines

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.androidbootcamp.kotlincoroutines.databinding.ActivitySearchBinding
import com.androidbootcamp.kotlincoroutines.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var searchBinding: ActivitySearchBinding
    private lateinit var view: View

    /*
        Não sou obrigado a só fazer uso do by lazy,
        mas essa parece ser a forma mais agradável.
    */
    private val searchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchBinding = ActivitySearchBinding.inflate(layoutInflater)
        view = searchBinding.root

        setContentView(view)

        searchBinding.searchText.doOnTextChanged { text, _, _, _ ->
            searchViewModel.searchByName(text.toString())
        }

        searchViewModel.response.observe(this) {
            searchBinding.contentTextView.text = it
        }
    }
}