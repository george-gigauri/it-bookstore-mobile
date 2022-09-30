package ge.herpi.itbookstore.details.domain.use_case

import android.util.Log
import ge.herpi.itbookstore.common.util.Resource
import ge.herpi.itbookstore.details.domain.model.BookDetails
import ge.herpi.itbookstore.details.domain.repository.BookDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val repository: BookDetailsRepository
) {

    suspend operator fun invoke(isbn13: String): Flow<Resource<BookDetails>> = flow {
        emit(Resource.Loading())
        try {
            val data = repository.getDetails(isbn13)
            emit(Resource.Success(data!!.toBookDetails()))
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("GetDetailsUseCase", "Exception: ${e.localizedMessage}")
            emit(Resource.Failure(e.message))
        }
    }.flowOn(Dispatchers.IO)
}