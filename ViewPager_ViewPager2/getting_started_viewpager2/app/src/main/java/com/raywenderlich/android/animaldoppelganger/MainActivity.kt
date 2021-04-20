/*
 * Copyright (c) 2020 Razeware LLC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 * 
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.animaldoppelganger

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.raywenderlich.android.animaldoppelganger.adapter.DoppelgangerAdapter
import com.raywenderlich.android.animaldoppelganger.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var doppelgangerNamesArray: Array<String>
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout

    private val callback = object : ViewPager2.OnPageChangeCallback() {

        // Quando uma nova página for selecionada faça algo.
        // Esse callback precisa ser removido!
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            Toast.makeText(
                    this@MainActivity,
                    "Mudei para $position",
                    Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Switch to AppTheme for displaying the activity
        setTheme(R.style.AppTheme)

        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        doppelgangerNamesArray = resources.getStringArray(R.array.doppelganger_names)

        viewPager2 = binding.viewPager
        tabLayout = binding.tabLayout

        viewPager2.adapter = DoppelgangerAdapter(this, doppelgangerNamesArray.size)

        viewPager2.registerOnPageChangeCallback(callback)

        // Pode ser feito no XML também.
        //viewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            // O split faz com que setamos um delimitador para cada posição.
            tab.text = doppelgangerNamesArray[position].split("|")[0]
        }.attach()

        //TODO:11 Force to RTL mode
        //viewPager2.layoutDirection = ViewPager2.LAYOUT_DIRECTION_RTL
        //viewPager2.layoutDirection = View.LAYOUT_DIRECTION_RTL

    }

    override fun onDestroy() {
        super.onDestroy()
        viewPager2.unregisterOnPageChangeCallback(callback)
    }
}
