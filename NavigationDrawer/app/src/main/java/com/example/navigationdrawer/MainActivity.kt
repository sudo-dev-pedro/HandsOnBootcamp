package com.example.navigationdrawer

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.example.navigationdrawer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mainBinding: ActivityMainBinding

    private val drawerLayout: DrawerLayout by lazy {
        mainBinding.drawerLayout
    }

    private val navView: NavigationView by lazy {
        mainBinding.navView
    }

    private val toolbar: Toolbar by lazy {
        findViewById(R.id.toolbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_about,
                R.id.nav_my_cards, R.id.nav_statement, R.id.nav_schedule
            ), drawerLayout
        )
        /**
         * Aqui é onde ele seta a ActionBar com o meu NavController.
         * Isso permite duas coisas:
         * - Que o icone de menu "hamburguer" apareça.
         * - Quando a transição entre as Fragments ocorrer, clicando nos items,
         * o title da ActionBar será o nome da Fragment correspondente.
         */
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /**
    Quando eu clicar para voltar eu preciso fechar o meu DrawerLayout
    retornado ele para a posição de Start (posição inicial).
    Caso ele já esteja fechado, o back segue o seu comportamento natural.
     */
    override fun onBackPressed() {
        if (mainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mainBinding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}