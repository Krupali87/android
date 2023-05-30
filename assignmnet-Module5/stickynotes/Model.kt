package com.example.stickynote



class Model
{
    private var stickyNote = ""
    private var id = 0

    fun getStickyNote(): String {
        return stickyNote
    }

    fun setStickyNote(stickyNote: String) {
        this.stickyNote = stickyNote
    }

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }
}