package ge.herpi.itbookstore.main.domain.repository

import ge.herpi.itbookstore.common.util.Resource
import ge.herpi.itbookstore.main.data.remote.dto.BooksResponse
import ge.herpi.itbookstore.main.domain.model.Book

interface BookRepository {
    suspend fun getNewBooks(): BooksResponse
}