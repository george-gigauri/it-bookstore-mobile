package ge.herpi.itbookstore.details.data.repository

import ge.herpi.itbookstore.details.data.remote.api.BookDetailsApi
import ge.herpi.itbookstore.details.data.remote.dto.BookDetailsDto
import ge.herpi.itbookstore.details.domain.repository.BookDetailsRepository
import retrofit2.HttpException

class BookDetailsRepositoryImpl(
    private val api: BookDetailsApi
) : BookDetailsRepository {

    override suspend fun getDetails(isbn13: String): BookDetailsDto? {
        return try {
            val response = api.getBookDetails(isbn13)
            if (response.isSuccessful) {
                response.body() ?: throw NullPointerException("Response body is empty")
            } else {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}