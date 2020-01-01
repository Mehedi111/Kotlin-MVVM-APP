package customview.gakk.com.mvvmsampleapp.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


/**
 *CREATED BY MEHEDI on 1/1/2020.
 */
private const val SAVED_AT_KEY = "key_saved_at"

class PreferenceProvider(
    context: Context
){

    private val appContext = context.applicationContext

    private val preferences: SharedPreferences get() = PreferenceManager.getDefaultSharedPreferences(appContext)


    fun saveLastSavedAt(saveAt: String){
        preferences.edit().putString(
            SAVED_AT_KEY,
            saveAt
        ).apply()
    }


    fun getLastSavedAt(): String?{
        return preferences.getString(SAVED_AT_KEY, null)
    }
}