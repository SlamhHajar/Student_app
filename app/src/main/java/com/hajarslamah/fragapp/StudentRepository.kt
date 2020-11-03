package com.hajarslamah.fragapp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import database.StudentDatabase
import java.util.*
import java.util.concurrent.Executors
private const val DATABASE_NAME = "product-database"
class StudentRepository  private constructor(context: Context) {
    private val database : StudentDatabase = Room.databaseBuilder(
        context.applicationContext,
        StudentDatabase::class.java,
        DATABASE_NAME    ).build()
    private val studentDao = database.studentDao()

    fun getStudents(): LiveData<List<Student>> = studentDao.getStudents()
    private val executor = Executors.newSingleThreadExecutor()

         fun getStudent(id: UUID): LiveData<Student?> = studentDao.getStudent(id)
             companion object {
               private var INSTANCE: StudentRepository? = null

                    fun initialize(context: Context) {
                         if (INSTANCE == null) {
                INSTANCE = StudentRepository(context)
            }        }
        fun get(): StudentRepository {
            return INSTANCE ?:
            throw IllegalStateException("ProductRepository must be initialized")
        }    }
    fun addStudent(student: Student) {
        executor.execute {
            studentDao.addStudent(student)
        }  }
    fun deletStudent(student: Student) {
        executor.execute {
            studentDao.deletStudent(student)
        }  }
    fun updateStudent(student: Student) {
        executor.execute {
            studentDao.updateStudent(student)
        }  }

}