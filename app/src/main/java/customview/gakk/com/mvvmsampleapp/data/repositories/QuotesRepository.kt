package customview.gakk.com.mvvmsampleapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import customview.gakk.com.mvvmsampleapp.data.db.AppDatabase
import customview.gakk.com.mvvmsampleapp.data.db.entities.Quote
import customview.gakk.com.mvvmsampleapp.data.network.ApiService
import customview.gakk.com.mvvmsampleapp.data.network.SafeApiRequest
import customview.gakk.com.mvvmsampleapp.data.preferences.PreferenceProvider
import customview.gakk.com.mvvmsampleapp.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


/**
 *CREATED BY MEHEDI on 1/1/2020.
 */

private const val MINIMUL_INTERVAL = 6;

class QuotesRepository(
    private val api: ApiService,
    private val db: AppDatabase,
    private val pref: PreferenceProvider
) : SafeApiRequest(){

    private val quotes = MutableLiveData<List<Quote>>()


    init {
        quotes.observeForever{
            saveQuotes(it)
        }
    }

    private suspend fun fetchQuotes(){
        //val lastSavedAt = pref.getLastSavedAt()
        if ( isFetchNeeded()){
            val response = apiRequest{api.getQuotes()}
            quotes.postValue(response.quotes)
        }
    }

    suspend fun getQuotes():LiveData<List<Quote>>{
        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuoteDao().getQuote()
        }
    }

    private fun isFetchNeeded(): Boolean {
        //return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUL_INTERVAL

        return true;
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io{
          //  pref.saveLastSavedAt(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
}