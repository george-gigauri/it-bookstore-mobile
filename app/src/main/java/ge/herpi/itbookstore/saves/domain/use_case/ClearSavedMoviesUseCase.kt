package ge.herpi.itbookstore.saves.domain.use_case

import ge.herpi.itbookstore.saves.domain.repository.SavedBooksRepository
import javax.inject.Inject

class ClearSavedMoviesUseCase @Inject constructor(
    private val repository: SavedBooksRepository
) {

    suspend operator fun invoke() {
        repository.clear()
    }
}