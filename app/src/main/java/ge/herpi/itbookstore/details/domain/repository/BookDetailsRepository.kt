package ge.herpi.itbookstore.details.domain.repository

import ge.herpi.itbookstore.details.data.remote.dto.BookDetailsDto

interface BookDetailsRepository {
    suspend fun getDetails(isbn13: String): BookDetailsDto?
}