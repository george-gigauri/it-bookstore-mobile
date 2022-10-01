package ge.herpi.itbookstore.search.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ge.herpi.itbookstore.main.domain.model.Book
import ge.herpi.itbookstore.search.data.remote.api.SearchApi

class SearchPagingSource(
    private val api: SearchApi,
    private val keyword: String
) : PagingSource<Int, Book>() {

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        val position = params.key ?: 1

        return try {
            val response = api.search(keyword, position)
            val result = response.body()

            LoadResult.Page(
                data = result?.bookDtos?.map { it.toBook() }.orEmpty(),
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (!result?.bookDtos.isNullOrEmpty()) position + 1 else null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}