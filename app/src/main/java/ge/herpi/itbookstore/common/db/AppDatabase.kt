package ge.herpi.itbookstore.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ge.herpi.itbookstore.details.data.local.dao.BookDetailsDao
import ge.herpi.itbookstore.details.data.local.entity.BookEntity
import ge.herpi.itbookstore.saves.data.local.dao.SavedBooksDao

@Database(
    entities = [BookEntity::class],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDetailsDao(): BookDetailsDao
    abstract fun savedBooksDao(): SavedBooksDao
}