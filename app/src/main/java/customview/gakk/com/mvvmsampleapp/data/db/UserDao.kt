package customview.gakk.com.mvvmsampleapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import customview.gakk.com.mvvmsampleapp.data.db.entities.CURRENT_USER_ID
import customview.gakk.com.mvvmsampleapp.data.db.entities.User


/**
 *CREATED BY MEHEDI on 12/29/2019.
 */

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getUser(): LiveData<User>

}