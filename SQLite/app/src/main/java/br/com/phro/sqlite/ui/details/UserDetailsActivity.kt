package br.com.phro.sqlite.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import br.com.phro.sqlite.MainActivity.Companion.ID
import br.com.phro.sqlite.database.entity.User
import br.com.phro.sqlite.databinding.ActivityUserDetailsBinding
import com.google.android.material.snackbar.Snackbar

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var detailsBinding: ActivityUserDetailsBinding
    private lateinit var detailsViewModel: UserDetailsViewModel
    private lateinit var user: User
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailsBinding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(detailsBinding.root)

        id = intent.getIntExtra(ID, 0)

        detailsViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(UserDetailsViewModel::class.java)

        detailsViewModel.createRepository(this)

        detailsViewModel.getUser(id)
        observeViews()
        onClickUpdate()
    }

    private fun observeViews() {
        detailsViewModel.user.observe(this) {
            detailsBinding.etUsername.setText(it.username)
            detailsBinding.etPassword.setText(it.password)
        }
    }

    private fun onClickUpdate() {
        detailsBinding.btnUpdate.setOnClickListener {
            user = User(
                id = id,
                username = detailsBinding.etUsername.text.toString(),
                password = detailsBinding.etPassword.text.toString()
            )
            detailsViewModel.updateUser(user)
            showSnackbar()
            detailsViewModel.getUser(id)
            observeViews()
        }
    }

    private fun showSnackbar() {
        val view: View = detailsBinding.detailsView
        Snackbar.make(view, "Atualizado com sucesso!", Snackbar.LENGTH_LONG).show()
    }

}