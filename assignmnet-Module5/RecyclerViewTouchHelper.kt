package com.example.todoapp2

import android.R
import android.content.DialogInterface
import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp2.Adapter.ToDoAdapter
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class RecyclerViewTouchHelper(var adapter: ToDoAdapter)  :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
{
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean
    {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int)
    {
        val position = viewHolder.adapterPosition
        if (direction == ItemTouchHelper.RIGHT)
        {
            val alertDialog = AlertDialog.Builder(adapter.getContext())
            alertDialog.setTitle("DELETE TASK?")
            alertDialog.setMessage("Are you sure?")
            alertDialog.setPositiveButton("YES",{ dialogInterface: DialogInterface, i: Int ->

                adapter.deleteTask(position)
            })
            alertDialog.setNegativeButton("NO",{ dialogInterface: DialogInterface, i: Int ->

                adapter.notifyItemChanged(position)
            })
            alertDialog.show()
        }
        else
        {
            adapter.editTask(position)
        }
    }
    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            .addSwipeRightBackgroundColor(ContextCompat.getColor(adapter.getContext(), R.color.holo_red_dark))
            .addSwipeLeftBackgroundColor(ContextCompat.getColor(adapter.getContext(), R.color.holo_green_dark))
            .addSwipeRightActionIcon(R.drawable.ic_menu_delete)
            .addSwipeLeftActionIcon(R.drawable.ic_menu_edit)
            .create()
            .decorate()
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}