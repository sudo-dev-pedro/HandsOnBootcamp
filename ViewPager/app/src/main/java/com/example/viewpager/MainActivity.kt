package com.example.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.viewpager.fragments.PrimeiraFragment
import com.example.viewpager.fragments.SegundaFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Referenciando as views
        val abas = findViewById<TabLayout>(R.id.abas)
        val nossaViewPager = findViewById<ViewPager>(R.id.viewpager)

        //Instanciamos o adapter e inserimos as fragments que queremos
        // exibir e seus títulos (abas)

        val adapter = AbasAdapter(supportFragmentManager)
        adapter.add(PrimeiraFragment(), "Primeira")
        adapter.add(SegundaFragment(), "Segunda")

        //Vinculamos o adapter criado ao adapter da ViewPager
        nossaViewPager.adapter = adapter

        //Vinculamos a ViewPager ao nosso TabLayout
        abas.setupWithViewPager(nossaViewPager)
    }
}