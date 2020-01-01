package customview.gakk.com.mvvmsampleapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import customview.gakk.com.mvvmsampleapp.data.db.entities.Quote
import customview.gakk.com.mvvmsampleapp.data.db.entities.User


/**
 *CREATED BY MEHEDI on 12/29/2019.
 */

@Database(
    entities = [User::class, Quote::class],

    version = 1
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getUserDao(): UserDao
    abstract fun getQuoteDao(): QuoteDao


    companion object{
        private var instance: AppDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?:buildDb(context).also {
                instance = it
            }
        }

        private fun buildDb(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "MyDB.db"
        ).build()
    }
}