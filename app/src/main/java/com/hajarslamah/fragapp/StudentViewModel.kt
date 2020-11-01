package com.hajarslamah.fragapp

import androidx.lifecycle.ViewModel

class StudentViewModel:ViewModel() {

    val students= mutableListOf<Student>()

    init {
        for (i in 0 until 5) {
         val student=Student()

            student.name= "Student"+ i
            student.num=i
               student.pass=i % 2 ==0
            students+=student
        }

        }
    fun addStudent(student: Student){
        students.add(student)

    }
    fun deletStudent(pos: Int){
        students.removeAt(pos)


    }







}