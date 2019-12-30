package customview.gakk.com.mvvmsampleapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import customview.gakk.com.mvvmsampleapp.R
import customview.gakk.com.mvvmsampleapp.data.db.AppDatabase
import customview.gakk.com.mvvmsampleapp.data.db.entities.User
import customview.gakk.com.mvvmsampleapp.data.network.ApiService
import customview.gakk.com.mvvmsampleapp.data.network.NetworkConnectionInterceptor
import customview.gakk.com.mvvmsampleapp.data.repositories.UserRepository
import customview.gakk.com.mvvmsampleapp.databinding.ActivityLoginBinding
import customview.gakk.com.mvvmsampleapp.ui.home.HomeActivity
import customview.gakk.com.mvvmsampleapp.util.hide
import customview.gakk.com.mvvmsampleapp.util.show
import customview.gakk.com.mvvmsampleapp.util.snackBar
import customview.gakk.com.mvvmsampleapp.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.progress_bar

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api= ApiService(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)

        val dataBinding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)

        dataBinding.authviewmodel = viewModel

        viewModel.authListener = this


       /* viewModel.getLoggedInUser().observe(this, Observer {
            user ->
            if (user != null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }

        })*/
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        //root_layout.snackBar("${user.name} is logged in")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackBar(message)
    }
}
