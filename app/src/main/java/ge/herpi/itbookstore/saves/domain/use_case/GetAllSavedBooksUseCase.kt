package ge.herpi.itbookstore.saves.domain.use_case

import androidx.lifecycle.LiveData
import ge.herpi.itbookstore.details.data.local.entity.BookEntity
import ge.herpi.itbookstore.saves.domain.repository.SavedBooksRepository
import javax.inject.Inject

class GetAllSavedBooksUseCase @Inject constructor(
    private val repository: SavedBooksRepository
) {

    operator fun invoke(): LiveData<List<BookEntity>> {
        return repository.getAll()
    }

}