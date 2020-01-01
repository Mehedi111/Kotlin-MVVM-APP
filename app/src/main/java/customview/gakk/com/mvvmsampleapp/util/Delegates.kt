package customview.gakk.com.mvvmsampleapp.util

import kotlinx.coroutines.*


/**
 *CREATED BY MEHEDI on 1/1/2020.
 */

fun<T> lazyDeferred(block: suspend CoroutineScope.()-> T):Lazy<Deferred<T>>{

    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY){
            block.invoke(this)
        }
    }
}