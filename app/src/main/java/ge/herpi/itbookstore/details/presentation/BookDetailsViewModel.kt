package ge.herpi.itbookstore.details.presentation

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.herpi.itbookstore.common.util.Resource
import ge.herpi.itbookstore.details.domain.model.BookDetails
import ge.herpi.itbookstore.details.domain.use_case.GetDetailsUseCase
import ge.herpi.itbookstore.details.domain.use_case.IsBookSavedUseCase
import ge.herpi.itbookstore.details.domain.use_case.RemoveSavedBookUseCase
import ge.herpi.itbookstore.details.domain.use_case.SaveBookUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val getDetails: GetDetailsUseCase,
    private val isBookSaved: IsBookSavedUseCase,
    private val saveBook: SaveBookUseCase,
    private val deleteSavedBook: RemoveSavedBookUseCase,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _errorMessage: MutableLiveData<String?> = MutableLiveData()
    val errorMessage: LiveData<String?> = _errorMessage
    private val _data: MutableLiveData<BookDetails> = MutableLiveData()
    val data: LiveData<BookDetails> = _data
    private val _isSaved: MutableLiveData<Boolean> = MutableLiveData()
    val isSaved: LiveData<Boolean> = _isSaved

    fun load(isbn13: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            getDetails(isbn13).collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        _isLoading.postValue(true)
                        _errorMessage.postValue(null)
                    }
                    is Resource.Failure -> {
                        Log.d("BookDetailsViewModel", "Failure: ${it.message}")
                        _isLoading.postValue(false)
                        _errorMessage.postValue(it.message)
                    }
                    is Resource.Success -> {
                        _isLoading.postValue(false)
                        _errorMessage.postValue(null)
                        _data.postValue(it.data!!)
                    }
                }
            }

            isBookSaved(isbn13).let {
                _isSaved.postValue(it)
            }
        }
    }

    fun save(book: BookDetails) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            saveBook(book)
            isBookSaved(book.isbn13).let {
                _isSaved.postValue(it)
            }
        }
    }

    fun remove(book: BookDetails) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            deleteSavedBook(book)
            isBookSaved(book.isbn13).let {
                _isSaved.postValue(it)
            }
        }
    }
}