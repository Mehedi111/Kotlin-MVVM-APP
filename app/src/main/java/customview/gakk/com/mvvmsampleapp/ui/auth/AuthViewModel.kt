package customview.gakk.com.mvvmsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import customview.gakk.com.mvvmsampleapp.data.db.entities.User
import customview.gakk.com.mvvmsampleapp.data.repositories.UserRepository
import customview.gakk.com.mvvmsampleapp.util.ApiException
import customview.gakk.com.mvvmsampleapp.util.Coroutines
import customview.gakk.com.mvvmsampleapp.util.NoInternetException


/**
 *CREATED BY MEHEDI on 12/29/2019.
 */
class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()


    fun onLoginButtonClicked(view: View){
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid")
            return
        }

        //success
        Coroutines.main{
          try {
              val authRes = repository.userLogin(email!!, password!!)

              authRes.user?.let {
                  authListener?.onSuccess(it)
                  repository.saveUser(it)
                  return@main
              }
              authListener?.onFailure(authRes.message!!)

          } catch (e: ApiException) {
              authListener?.onFailure(e.message!!)
          } catch (e: NoInternetException){
              authListener?.onFailure(e.message!!)

          }
        }


    }
}