package ge.herpi.itbookstore.details.data.remote.api

import ge.herpi.itbookstore.details.data.remote.dto.BookDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookDetailsApi {

    @GET("/1.0/books/{isbn13}")
    suspend fun getBookDetails(
        @Path("isbn13") isbn13: String
    ): Response<BookDetailsDto>
}