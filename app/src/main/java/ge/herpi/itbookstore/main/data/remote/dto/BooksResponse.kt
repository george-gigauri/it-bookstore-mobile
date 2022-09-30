package ge.herpi.itbookstore.main.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BooksResponse(
    @SerializedName("books")
    val books: List<BookDto>,
    @SerializedName("total")
    val total: String,
    @SerializedName("error")
    val error: String
)