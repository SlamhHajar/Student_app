package com.hajarslamah.fragapp

import androidx.lifecycle.ViewModel

class StudentViewModel:ViewModel() {

    val students= mutableListOf<Student>()

//    init {
//        for (i in 0 until 5) {
//         val student=Student()
//
//            student.name= "Student"+ i
//            student.num=i
//               student.pass=i % 2 ==0
//            students+=student
//        }
//
//        }
private val studentRepository = StudentRepository.get()
    val studentListLiveData  = studentRepository.getStudents()

    fun addStudent(student: Student){
        studentRepository.addStudent(student)

    }
    fun updateStudent(student: Student){
        studentRepository.updateStudent(student)

    }
    fun deletStudent(student: Student){
        studentRepository.deletStudent(student)


    }







}