package database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hajarslamah.fragapp.Student
import java.util.*

@Dao
interface StudentDao {
    @Query("SELECT * FROM student")
    // fun getStudents(): List<Product>
    fun getStudents(): LiveData<List<Student>>
    @Query("SELECT * FROM student WHERE id=(:id)")
    fun getStudent(id: UUID): LiveData<Student?>
    // fun getStudent(id: UUID): Product?


    @Update
    fun updateStudent(student: Student)
    @Insert
    fun addStudent(product: Student)
    @Delete
    fun deletStudent(student: Student)

}

