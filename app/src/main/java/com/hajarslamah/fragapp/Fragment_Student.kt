package com.hajarslamah.fragapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_student.*

class Fragment_Student: Fragment() {

    private val studentListViewModel: StudentViewModel by lazy {
        ViewModelProviders.of(this).get(StudentViewModel::class.java)
    }
    companion object {
        fun newInstance():Fragment_Student {
            return Fragment_Student()
        }
    }
    private var adapter: StudentAdapter? = null
private lateinit var studentRecyclerView: RecyclerView
//private lateinit var clickButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
           }
override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?    ): View? {
    //  val view = inflater.inflate(R.layout.fragment_student, container, false)
    // clickButton = view.findViewById(R.id.click) as Button
    val view = inflater.inflate(R.layout.fragment_student_list, container, false)
    studentRecyclerView = view.findViewById(R.id.student_recycler_view) as RecyclerView
    studentRecyclerView.layoutManager = LinearLayoutManager(context)
    updateView()
    return view
}
    private fun updateView() {
        val students = studentListViewModel.students
        adapter = StudentAdapter(students)
        studentRecyclerView.adapter = adapter    }

    private inner class StudentHolder(view: View)
        : RecyclerView.ViewHolder(view) , View.OnClickListener{
        private lateinit var student: Student

        val st_NameTextView: TextView = itemView.findViewById(R.id.student_name)
        val st_NumTextView: TextView = itemView.findViewById(R.id.stud_num)
        val st_passTextView: TextView = itemView.findViewById(R.id.stud_pass)
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(student:Student ) {
            this.student = student
            st_NameTextView.text = this.student.name
            st_NumTextView.text="Number: "+this.student.num.toString()
            st_passTextView.text="Pass:  "+this.student.pass.toString()
        }

        override fun onClick(p0: View?) {
            Toast.makeText(context, "${student.name}!", Toast.LENGTH_SHORT)
              .show()
        }
    }
    private inner class StudentAdapter(var students: List<Student>)
        : RecyclerView.Adapter<StudentHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
            val view = layoutInflater.inflate(R.layout.fragment_student, parent, false)
            return StudentHolder(view)
        }

        override fun onBindViewHolder(holder: StudentHolder, position: Int) {
            val student=students[position]
            holder.apply {
                holder.bind(student)
}}
        override fun getItemCount()=students.size

    }

        override fun onStart() {
        super.onStart()
       // clickButton.setOnClickListener {
         //   Toast.makeText(context, " Hi I'm Hajar *__-", Toast.LENGTH_SHORT) .show()
       // }
    }

}



