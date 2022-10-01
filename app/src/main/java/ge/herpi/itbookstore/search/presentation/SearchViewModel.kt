package ge.herpi.itbookstore.search.presentation

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.herpi.itbookstore.main.domain.model.Book
import ge.herpi.itbookstore.search.domain.use_case.SearchBookUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val search: SearchBookUseCase
) : ViewModel() {

    private var job: Job? = null
    private val query: MutableLiveData<String> = MutableLiveData()
    val searchData: LiveData<PagingData<Book>> =
        query.switchMap { search(it) }.cachedIn(viewModelScope)

    fun searchWithQuery(keyword: String) {
        job?.cancel()
        job = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                delay(1500)
                query.postValue(keyword)
            }
        }
    }
}