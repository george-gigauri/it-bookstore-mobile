package ge.herpi.itbookstore.saves.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.herpi.itbookstore.details.data.local.entity.BookEntity
import ge.herpi.itbookstore.saves.domain.use_case.ClearSavedMoviesUseCase
import ge.herpi.itbookstore.saves.domain.use_case.GetAllSavedBooksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SavedBooksViewModel @Inject constructor(
    private val getAllSavedBooks: GetAllSavedBooksUseCase,
    private val clearSavedMovies: ClearSavedMoviesUseCase
) : ViewModel() {

    val data: LiveData<List<BookEntity>> = getAllSavedBooks()

    fun clear() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            clearSavedMovies()
        }
    }
}