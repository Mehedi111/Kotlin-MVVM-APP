package customview.gakk.com.mvvmsampleapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import customview.gakk.com.mvvmsampleapp.R
import customview.gakk.com.mvvmsampleapp.databinding.ActivityLoginBinding
import customview.gakk.com.mvvmsampleapp.util.toast

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        dataBinding.authviewmodel = viewModel

        viewModel.authListener = this
    }

    override fun onStarted() {
        toast("login started !")
    }

    override fun onSuccess() {
        toast("login success !")

    }

    override fun onFailure(message: String) {
        toast(message)
    }
}
