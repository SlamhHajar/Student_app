package com.hajarslamah.fragapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DeleteDialogFragment: DialogFragment()  {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return AlertDialog.Builder(requireContext(),R.style.ThemeOverlay_AppCompat_Dialog_Alert)
    .setView(view)
        .setMessage("Are you sure you want to Delete?")
    .setPositiveButton("Yes"){ dialog,_ ->
        val std=Student()
        targetFragment?.let { fragment ->
            (fragment as DeletedCallbacks).deletedStudent(std)
        }
    }.setNegativeButton("Cancel"){dialog,_ ->
        dialog.cancel()
    }.create()

}

interface DeletedCallbacks {

    fun deletedStudent(student: Student)

}}
