package ge.herpi.itbookstore.main.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.herpi.itbookstore.common.util.Resource
import ge.herpi.itbookstore.main.domain.model.Book
import ge.herpi.itbookstore.main.domain.use_case.GetNewBooksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNewBooks: GetNewBooksUseCase
) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _errorMessage: MutableLiveData<String?> = MutableLiveData()
    val errorMessage: LiveData<String?> = _errorMessage
    private val _books: MutableLiveData<List<Book>> = MutableLiveData()
    val books: LiveData<List<Book>> = _books

    init {
        load()
    }

    private fun load() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            getNewBooks().collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        _errorMessage.postValue(null)
                        _isLoading.postValue(true)
                    }
                    is Resource.Failure -> {
                        Log.d(
                            "MainViewModel",
                            "Resource.Failure state.\nError Message: ${it.message}"
                        )
                        _errorMessage.postValue(it.message)
                        _isLoading.postValue(false)
                    }
                    is Resource.Success -> {
                        _errorMessage.postValue(null)
                        _isLoading.postValue(false)
                        _books.postValue(it.data.orEmpty())
                    }
                }
            }
        }
    }
}