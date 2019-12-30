package customview.gakk.com.mvvmsampleapp.data.network.responses

import customview.gakk.com.mvvmsampleapp.data.db.entities.User


/**
 *CREATED BY MEHEDI on 12/30/2019.
 */
data class AuthResponse (
    val isSuccessful : String? = null,
    val message: String? = null,
    val user: User? = null
){

}