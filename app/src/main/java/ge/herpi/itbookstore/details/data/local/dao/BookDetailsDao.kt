package ge.herpi.itbookstore.details.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ge.herpi.itbookstore.details.data.local.entity.BookEntity

@Dao
interface BookDetailsDao {

    @Query("SELECT EXISTS (SELECT * FROM book WHERE isbn13 = :isbn13)")
    suspend fun exists(isbn13: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(book: BookEntity)

    @Query("DELETE FROM book WHERE isbn13=:isbn13")
    suspend fun delete(isbn13: String)
}