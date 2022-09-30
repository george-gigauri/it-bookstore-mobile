package ge.herpi.itbookstore.details.data.remote.dto

import com.google.gson.annotations.SerializedName
import ge.herpi.itbookstore.details.domain.model.BookDetails
import ge.herpi.itbookstore.details.domain.model.Pdf

data class BookDetailsDto(
    @SerializedName("authors")
    val authors: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("error")
    val error: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("isbn10")
    val isbn10: String,
    @SerializedName("isbn13")
    val isbn13: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("pages")
    val pages: String,
    @SerializedName("pdf")
    val pdf: Map<String, String>?,
    @SerializedName("price")
    val price: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("year")
    val year: String
) {

    fun toBookDetails(): BookDetails {
        return BookDetails(
            authors,
            desc,
            error,
            image,
            isbn10,
            isbn13,
            language,
            pages,
            pdf?.entries?.map { Pdf(it.key, it.value) }.orEmpty(),
            price,
            publisher,
            rating, subtitle, title, url, year
        )
    }
}