package customview.gakk.com.mvvmsampleapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import customview.gakk.com.mvvmsampleapp.data.db.entities.Quote


/**
 *CREATED BY MEHEDI on 1/1/2020.
 */
@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllQuotes(quote: List<Quote>)

    @Query("SELECT * FROM Quote")
    fun getQuote(): LiveData<List<Quote>>
}