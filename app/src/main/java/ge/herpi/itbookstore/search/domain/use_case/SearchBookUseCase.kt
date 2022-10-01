package ge.herpi.itbookstore.search.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import ge.herpi.itbookstore.search.data.paging.SearchPagingSource
import ge.herpi.itbookstore.search.data.remote.api.SearchApi
import javax.inject.Inject

class SearchBookUseCase @Inject constructor(
    private val api: SearchApi
) {

    operator fun invoke(keyword: String) = Pager(
        config = PagingConfig(
            pageSize = 10,
            initialLoadSize = 15
        ),
        pagingSourceFactory = { SearchPagingSource(api, keyword) }
    ).liveData
}