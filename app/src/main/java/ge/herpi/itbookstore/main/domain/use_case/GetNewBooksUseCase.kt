package ge.herpi.itbookstore.main.domain.use_case

import ge.herpi.itbookstore.common.util.Resource
import ge.herpi.itbookstore.main.domain.model.Book
import ge.herpi.itbookstore.main.domain.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetNewBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Book>>> = flow {
        emit(Resource.Loading())
        try {
            val data = repository.getNewBooks()
            emit(Resource.Success(data.books.map { it.toBook() }))
        } catch (e: Exception) {
            emit(Resource.Failure(e.message))
        }
    }.flowOn(Dispatchers.IO)
}