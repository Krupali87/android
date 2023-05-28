package com.example.todoapp2

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.todoapp2.Database.DatabaseHelper
import com.example.todoapp2.Model.todoModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class addnewtask : BottomSheetDialogFragment()
{
    companion object
    {
        fun newInstance() : addnewtask
        {
            return addnewtask()
        }
         var TAG = "addnewtask"
    }

    lateinit var edt1 : EditText
    lateinit var btnsave : Button

    lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        return inflater.inflate(R.layout.new_task,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        edt1 = view.findViewById(R.id.edt1)
        btnsave = view.findViewById(R.id.btnsave)

        databaseHelper = DatabaseHelper(requireActivity())

        var isUpdate = false

        val bundle = arguments
        if (bundle!=null)
        {
            isUpdate = true
            val task = bundle.getString("task")
            edt1.setText(task)

            if (task!!.isNotEmpty())
            {
                btnsave.isEnabled= false
            }
        }
        edt1.addTextChangedListener(onTextChanged = { charSequence: CharSequence?, i: Int, i1: Int, i2: Int ->

            if (charSequence.toString() == "")
            {
                btnsave.isEnabled = false
                btnsave.setBackgroundColor(Color.GRAY)
            }
            else
            {
                btnsave.isEnabled = true
                btnsave.setBackgroundColor(resources.getColor(R.color.black))
            }
        })

        btnsave.setOnClickListener {

            val text = edt1.text.toString()
            if (isUpdate)
            {
                databaseHelper.updateTask(bundle!!.getInt("i"),text)
            }
            else
            {
                val item = todoModel()
                item.setTask(text)
                item.setStatus(0)
                databaseHelper.insertTask(item)
            }
            dismiss()

        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        val activity = activity
        if (activity is OnDialogCloseListener)
        {
            activity.onDialogClose(dialog)
        }
    }
}