package customview.gakk.com.mvvmsampleapp.ui.auth

import androidx.lifecycle.LiveData
import customview.gakk.com.mvvmsampleapp.data.db.entities.User


/**
 *CREATED BY MEHEDI on 12/29/2019.
 */
interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}