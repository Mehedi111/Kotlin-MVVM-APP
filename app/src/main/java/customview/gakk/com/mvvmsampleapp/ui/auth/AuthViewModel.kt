package customview.gakk.com.mvvmsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel


/**
 *CREATED BY MEHEDI on 12/29/2019.
 */
class AuthViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null


    fun onLoginButtonClicked(view: View){
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid")
            return
        }

        authListener?.onSuccess()
        //success


    }
}