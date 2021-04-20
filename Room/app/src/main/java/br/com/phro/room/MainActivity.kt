package br.com.phro.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.phro.room.App.Companion.database
import br.com.phro.room.database.entity.User
import br.com.phro.room.databinding.ActivityMainBinding
import br.com.phro.room.ui.details.UserDetailsActivity
import br.com.phro.room.ui.main.MainViewModel
import br.com.phro.sqlite.ui.main.adapter.UserAdapter

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        userAdapter = UserAdapter((listOf())) {
            navigateToDetails(it)
        }

        initViews()

        mainViewModel.createRepository(database.userDAO())

        onClickInsert()
        observeUsers()
        onClickDeleteUsers()
        onClickDeleteByUsername()
    }

    private fun onClickInsert() {
        mainBinding.btnInsert.setOnClickListener {
            val user = User(
                username = mainBinding.tvHelloWorld.text.toString(),
                password = mainBinding.tvPassword.text.toString()
            )

            val hiddenUser = User(
                username = mainBinding.tvHiddenHelloWorld.text.toString(),
                password = mainBinding.tvHiddenPassword.text.toString()
            )

            mainViewModel.addUser(user)
            mainViewModel.addUser(hiddenUser)
        }
    }

    private fun observeUsers() {
        mainViewModel.usersList.observe(this) {
            userAdapter.updateUsersList(it)
        }
    }

    private fun onClickDeleteUsers() {
        mainBinding.btnDelete.setOnClickListener {
            mainViewModel.deleteAllUsers()
            userAdapter.updateUsersList(emptyList())
        }
    }

    private fun onClickDeleteByUsername() {
        mainBinding.btnDeleteByUsername.setOnClickListener {
            mainViewModel.deleteUserByName("Pedro")
        }
    }

    private fun initViews() {
        mainBinding.rvUsers.adapter = userAdapter
        mainBinding.rvUsers.setHasFixedSize(true)
    }

    private fun navigateToDetails(id: Int?) {
        val intent = Intent(this, UserDetailsActivity::class.java)

        intent.apply {
            putExtra(ID, id)
        }

        startActivity(intent)
    }

    companion object {
        const val ID = "ID"
    }
}