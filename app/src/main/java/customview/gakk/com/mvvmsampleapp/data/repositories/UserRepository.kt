package customview.gakk.com.mvvmsampleapp.data.repositories

import customview.gakk.com.mvvmsampleapp.data.db.AppDatabase
import customview.gakk.com.mvvmsampleapp.data.db.entities.User
import customview.gakk.com.mvvmsampleapp.data.network.ApiService
import customview.gakk.com.mvvmsampleapp.data.network.SafeApiRequest
import customview.gakk.com.mvvmsampleapp.data.network.responses.AuthResponse


/**
 *CREATED BY MEHEDI on 12/29/2019.
 */
class UserRepository(
    private val api: ApiService,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun userSignup(
        name: String,
        email: String,
        password: String
    ): AuthResponse {
        return apiRequest{api.userSignup(name, email, password)}
    }


    suspend fun saveUser(user: User) = db.getUserDao().insert(user)

    fun getUser() = db.getUserDao().getUser()


}