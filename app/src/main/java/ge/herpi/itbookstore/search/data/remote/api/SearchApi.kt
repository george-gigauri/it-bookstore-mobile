package ge.herpi.itbookstore.search.data.remote.api

import ge.herpi.itbookstore.search.data.remote.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchApi {

    @GET("/1.0/search/{keyword}/{page}")
    suspend fun search(
        @Path("keyword") keyword: String,
        @Path("page") page: Int = 1
    ): Response<SearchResponse>
}