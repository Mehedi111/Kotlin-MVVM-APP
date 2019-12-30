package customview.gakk.com.mvvmsampleapp.util

import java.io.IOException


/**
 *CREATED BY MEHEDI on 12/30/2019.
 */

class ApiException(message: String) : IOException(message)

class NoInternetException(message: String): IOException(message)