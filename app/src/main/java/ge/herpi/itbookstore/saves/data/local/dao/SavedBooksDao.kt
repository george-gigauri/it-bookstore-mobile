package ge.herpi.itbookstore.saves.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ge.herpi.itbookstore.details.data.local.entity.BookEntity

@Dao
interface SavedBooksDao {
    @Query("SELECT * FROM book")
    fun getAll(): LiveData<List<BookEntity>>

    @Query("DELETE FROM book")
    suspend fun clear()
}