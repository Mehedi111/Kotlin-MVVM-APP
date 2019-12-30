package customview.gakk.com.mvvmsampleapp.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 *CREATED BY MEHEDI on 12/30/2019.
 */

object Coroutines{
    fun main(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main)
            .launch {
                work()
            }
}