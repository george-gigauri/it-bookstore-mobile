package ge.herpi.itbookstore.details.domain.use_case

import ge.herpi.itbookstore.details.data.local.dao.BookDetailsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IsBookSavedUseCase @Inject constructor(
    private val db: BookDetailsDao
) {

    suspend operator fun invoke(isbn13: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext db.exists(isbn13)
    }
}