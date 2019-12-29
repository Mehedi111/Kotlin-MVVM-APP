package customview.gakk.com.mvvmsampleapp.ui.auth


/**
 *CREATED BY MEHEDI on 12/29/2019.
 */
interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}