package ge.herpi.itbookstore.main.data.remote.api

import ge.herpi.itbookstore.main.data.remote.dto.BooksResponse
import retrofit2.Response
import retrofit2.http.GET

interface BookService {

    @GET("/1.0/new")
    suspend fun getNewBooks(): Response<BooksResponse>
}