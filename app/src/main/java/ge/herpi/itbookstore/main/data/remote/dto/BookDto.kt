package ge.herpi.itbookstore.main.data.remote.dto

import com.google.gson.annotations.SerializedName
import ge.herpi.itbookstore.main.domain.model.Book

data class BookDto(
    @SerializedName("image")
    val image: String,
    @SerializedName("isbn13")
    val isbn13: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
) {
    fun toBook(): Book = Book(
        image, isbn13, price, subtitle, title, url
    )
}