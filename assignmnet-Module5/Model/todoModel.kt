package com.example.todoapp2.Model



class todoModel
{
    private var task: String? = null
    private var id = 0
    private  var status:Int = 0

    fun getTask(): String? {
        return task
    }

    fun setTask(task: String?) {
        this.task = task
    }

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getStatus(): Int {
        return status
    }

    fun setStatus(status: Int) {
        this.status = status
    }





}