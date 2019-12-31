package customview.gakk.com.mvvmsampleapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import customview.gakk.com.mvvmsampleapp.R
import customview.gakk.com.mvvmsampleapp.data.db.entities.User
import customview.gakk.com.mvvmsampleapp.databinding.ActivitySignupBinding
import customview.gakk.com.mvvmsampleapp.ui.home.HomeActivity
import customview.gakk.com.mvvmsampleapp.util.hide
import customview.gakk.com.mvvmsampleapp.util.show
import customview.gakk.com.mvvmsampleapp.util.snackBar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.progress_bar
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val provider: AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding: ActivitySignupBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_signup)

        val  viewModel = ViewModelProviders.of(this, provider).get(AuthViewModel::class.java)
        dataBinding.authviewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }

        })

    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackBar(message)
    }
}