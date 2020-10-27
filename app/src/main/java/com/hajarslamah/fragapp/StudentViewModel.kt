package com.hajarslamah.fragapp

import androidx.lifecycle.ViewModel

class StudentViewModel:ViewModel() {

    val students= mutableListOf<Student>()
    init {
        for (i in 0 until 200) {
         val student=Student()

            student.name= "Student"+ i
            student.num=i
               student.pass=i % 2 ==1
            students+=student
        }
        }
}