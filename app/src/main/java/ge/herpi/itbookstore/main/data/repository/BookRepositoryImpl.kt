package ge.herpi.itbookstore.main.data.repository

import ge.herpi.itbookstore.main.data.remote.api.BookService
import ge.herpi.itbookstore.main.data.remote.dto.BooksResponse
import ge.herpi.itbookstore.main.domain.repository.BookRepository
import retrofit2.HttpException

class BookRepositoryImpl(
    private val api: BookService
) : BookRepository {

    override suspend fun getNewBooks(): BooksResponse {
        return try {
            val response = api.getNewBooks()
            if (response.isSuccessful) {
                response.body() ?: throw NullPointerException("Response body is empty")
            } else {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            BooksResponse(
                emptyList(),
                "0",
                e.message.orEmpty()
            )
        }
    }
}