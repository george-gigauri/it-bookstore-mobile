package ge.herpi.itbookstore.details.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ge.herpi.itbookstore.details.domain.model.BookDetails
import ge.herpi.itbookstore.main.domain.model.Book

@Entity(tableName = "book")
data class BookEntity(
    @PrimaryKey(autoGenerate = false)
    val isbn13: String,
    val authors: String,
    val description: String,
    val image: String,
    val isbn10: String,
    val language: String,
    val pages: String,
    val price: String,
    val publisher: String,
    val rating: String,
    val subtitle: String,
    val title: String,
    val url: String,
    val year: String
) {

    fun toBook(): Book = Book(
        image, isbn13, price, subtitle, title, url
    )

    fun toBookDetails(): BookDetails = BookDetails(
        authors,
        description,
        "",
        image,
        isbn10,
        isbn13,
        language,
        pages,
        emptyList(),
        price,
        publisher,
        rating, subtitle, title, url, year
    )
}
