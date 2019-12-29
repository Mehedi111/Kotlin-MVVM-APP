package customview.gakk.com.mvvmsampleapp.util

import android.content.Context
import android.widget.Toast


/**
 *CREATED BY MEHEDI on 12/29/2019.
 */

fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}