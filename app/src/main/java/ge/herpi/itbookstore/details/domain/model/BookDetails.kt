package ge.herpi.itbookstore.details.domain.model

data class BookDetails(
    val authors: String,
    val desc: String,
    val error: String,
    val image: String,
    val isbn10: String,
    val isbn13: String,
    val language: String,
    val pages: String,
    val pdf: List<Pdf>,
    val price: String,
    val publisher: String,
    val rating: String,
    val subtitle: String,
    val title: String,
    val url: String,
    val year: String
)
