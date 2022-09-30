package ge.herpi.itbookstore.saves.domain.repository

import androidx.lifecycle.LiveData
import ge.herpi.itbookstore.details.data.local.entity.BookEntity

interface SavedBooksRepository {
    fun getAll(): LiveData<List<BookEntity>>
    suspend fun clear()
}