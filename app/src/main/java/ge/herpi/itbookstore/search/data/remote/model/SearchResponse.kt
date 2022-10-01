package ge.herpi.itbookstore.search.data.remote.model

import com.google.gson.annotations.SerializedName
import ge.herpi.itbookstore.main.data.remote.dto.BookDto

data class SearchResponse(
    @SerializedName("books")
    val bookDtos: List<BookDto>,
    @SerializedName("page")
    val page: String,
    @SerializedName("total")
    val total: String
)