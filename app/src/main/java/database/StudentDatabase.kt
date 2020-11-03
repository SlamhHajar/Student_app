package database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hajarslamah.fragapp.Student

@Database(entities = [ Student::class ], version=1, exportSchema = false)
@TypeConverters(StudentTypeConverters::class)
abstract class StudentDatabase: RoomDatabase() {
    abstract fun studentDao(): StudentDao
}
