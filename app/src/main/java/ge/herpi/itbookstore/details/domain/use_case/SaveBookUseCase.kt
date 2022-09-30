package ge.herpi.itbookstore.details.domain.use_case

import ge.herpi.itbookstore.details.data.local.dao.BookDetailsDao
import ge.herpi.itbookstore.details.domain.model.BookDetails
import javax.inject.Inject

class SaveBookUseCase @Inject constructor(
    private val db: BookDetailsDao
) {

    suspend operator fun invoke(book: BookDetails): Boolean {
        return try {
            db.save(book.toBookEntity())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}