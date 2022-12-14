package ge.herpi.itbookstore.common.di

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ge.herpi.itbookstore.common.db.AppDatabase
import ge.herpi.itbookstore.details.data.local.dao.BookDetailsDao
import ge.herpi.itbookstore.details.data.remote.api.BookDetailsApi
import ge.herpi.itbookstore.details.data.repository.BookDetailsRepositoryImpl
import ge.herpi.itbookstore.details.domain.repository.BookDetailsRepository
import ge.herpi.itbookstore.main.data.remote.api.BookService
import ge.herpi.itbookstore.main.data.repository.BookRepositoryImpl
import ge.herpi.itbookstore.main.domain.repository.BookRepository
import ge.herpi.itbookstore.saves.data.local.dao.SavedBooksDao
import ge.herpi.itbookstore.saves.data.repository.SavedBooksRepositoryImpl
import ge.herpi.itbookstore.saves.domain.repository.SavedBooksRepository
import ge.herpi.itbookstore.search.data.remote.api.SearchApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "itbookstore")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideBookDetailsDao(db: AppDatabase): BookDetailsDao = db.bookDetailsDao()

    @Provides
    @Singleton
    fun provideSavedBooksDao(db: AppDatabase): SavedBooksDao = db.savedBooksDao()
}