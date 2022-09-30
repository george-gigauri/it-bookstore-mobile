package ge.herpi.itbookstore.saves.data.repository

import androidx.lifecycle.LiveData
import ge.herpi.itbookstore.details.data.local.entity.BookEntity
import ge.herpi.itbookstore.saves.data.local.dao.SavedBooksDao
import ge.herpi.itbookstore.saves.domain.repository.SavedBooksRepository

class SavedBooksRepositoryImpl(
    private val db: SavedBooksDao
) : SavedBooksRepository {

    override fun getAll(): LiveData<List<BookEntity>> {
        return db.getAll()
    }

    override suspend fun clear() {
        db.clear()
    }
}