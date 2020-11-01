package com.hajarslamah.fragapp

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_student.*
import kotlinx.android.synthetic.main.fragment_student_list.*
import java.util.*

class Fragment_Student: Fragment(),InputDialogFragment.Callbacks {

    private val studentListViewModel: StudentViewModel by lazy {
        ViewModelProviders.of(this).get(StudentViewModel::class.java)
    }
    companion object {
        fun newInstance():Fragment_Student {
            return Fragment_Student()

        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.student_mune, menu)    }
    private var adapter: StudentAdapter? = null
private lateinit var studentRecyclerView: RecyclerView
private lateinit var newStudentButton: ImageButton
    private lateinit var emptyTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
           }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {  
        return when (item.itemId) {      
            R.id.newStudent -> {       
                val student = Student()
                //studentListViewModel.addStudent(Student(UUID.randomUUID(),99,"Hajar",true))
                //Toast.makeText(context, " add student", Toast.LENGTH_SHORT) .show()
               // updateView()
                InputDialogFragment().apply{
                    setTargetFragment(this@Fragment_Student,0)
                    show(this@Fragment_Student.requireFragmentManager(),"Input")

                }

         true
            }
            else -> return super.onOptionsItemSelected(item)
        }}

override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?    ): View? {
    //  val view = inflater.inflate(R.layout.fragment_student, container, false)

    val view = inflater.inflate(R.layout.fragment_student_list, container, false)
    //deletButton = view.findViewById(R.id.stud_delet) as ImageButton
     newStudentButton = view.findViewById(R.id.newStudent) as ImageButton
   emptyTextView = view.findViewById(R.id.empty_view) as TextView
    studentRecyclerView = view.findViewById(R.id.student_recycler_view) as RecyclerView
    studentRecyclerView.layoutManager = LinearLayoutManager(context)//to manage the calling of oncreate and onbind and allow you how to show the UI
    newStudentButton.setOnClickListener {
        val student = Student()
        InputDialogFragment().apply{
            setTargetFragment(this@Fragment_Student,0)
            show(this@Fragment_Student.requireFragmentManager(),"Input")

        }}
    updateView()
    return view
}
    private fun updateView() {

        val students = studentListViewModel.students
        adapter = StudentAdapter(students)
        studentRecyclerView.adapter = adapter
        }
//////////////////////////////////////////////////////////////////Holder to store the data and inflate to item////////////////////////////////
    private inner class StudentHolder(view: View)
        : RecyclerView.ViewHolder(view) , View.OnClickListener{
             private lateinit var student: Student
          //   val emptyTextView: TextView = itemView.findViewById(R.id.empty_view)
                val st_NumTextView: TextView = itemView.findViewById(R.id.stud_num)
                   val st_NameTextView: TextView = itemView.findViewById(R.id.student_name)
                    val st_passTextView: TextView = itemView.findViewById(R.id.stud_pass)
                       val deleteButton: ImageButton = itemView.findViewById(R.id.stud_delet)
                 //  val newButton: Button = itemView.findViewById(R.id.newStudent)
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
    ////////////////////////////////////////////////////Adapter using holder//////////////////////////
    private inner class StudentAdapter(var students: List<Student>)
        : RecyclerView.Adapter<StudentHolder>() {
        ////////////////////////////// that's fun call once On created to the  first items
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
            val view = layoutInflater.inflate(R.layout.fragment_student, parent, false)
            return StudentHolder(view)
        }


///////////////////////////////////////////////////Fun @onBindViewHolder  that's call at to connect between tha data exaist on array and the item on holder
        override fun onBindViewHolder(holder: StudentHolder, position: Int) {

            val student=students[position]
            holder.apply {
                holder.bind(student)

          deleteButton.setOnClickListener {
              deletedStudent(position)
            // studentListViewModel.students.removeAt(position)
                  //  notifyDataSetChanged()
                   // updateView()
                   // Toast.makeText(context, " Hi I'm Hajar *__-", Toast.LENGTH_SHORT) .show()
}

            }}
        ////////////////////////to return  size of array
        override fun getItemCount(): Int {
            if(students.isEmpty()){
                emptyTextView.visibility=View.VISIBLE
                newStudentButton.visibility=View.VISIBLE
            }else{
                emptyTextView.visibility=View.GONE
                newStudentButton.visibility=View.GONE
            }

            return students.size
        }


    }

       override fun onStart() {

  super.onStart()

       // clickButton.setOnClickListener {
         //   Toast.makeText(context, " Hi I'm Hajar *__-", Toast.LENGTH_SHORT) .show()
       // }

    }

    override fun onStudentAdded(student: Student) {
     studentListViewModel.addStudent(student)
        updateView()
    }

    override fun deletedStudent(pos: Int) {
        studentListViewModel.deletStudent(pos)
        updateView()
    }

}



