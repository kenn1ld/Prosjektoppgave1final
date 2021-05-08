package no.uia.ikt205.mybooks.books

import android.content.Context
import no.uia.ikt205.mybooks.books.data.Book
import no.uia.ikt205.mybooks.books.data.Huskeliste

class HuskelisteSublistDepositoryManager {
    private lateinit var todosCollection : MutableList<Huskeliste>

    var onTodos: ((List<Huskeliste>) -> Unit)? = null
    var onTodosUpdate: ((huskeliste: Huskeliste) -> Unit)? = null

    fun load(){
        todosCollection= mutableListOf(
                Huskeliste("Egg"),
                Huskeliste("Melk"),
                Huskeliste("Nøtter"),
                Huskeliste("Brus"),
        )

        onTodos?.invoke(todosCollection)
    }

    fun deleteDoneTasks() {
        todosCollection.removeAll { tasksCollection ->
            tasksCollection.checkBox
        }
        onTodos?.invoke(todosCollection)
    }

    fun removeElement(element:Huskeliste) {
        todosCollection.remove(element)
        onTodos?.invoke(todosCollection)
    }

    fun loadTasks(context: Context) {
        todosCollection = mutableListOf()
        onTodos?.invoke(todosCollection)
    }

    fun updateTask(huskeliste: Huskeliste) {
        onTodosUpdate?.invoke(huskeliste)
    }

    fun addTask(task: Huskeliste) {
        todosCollection.add(task)
        onTodos?.invoke(todosCollection)
    }


    companion object {
        val instance = HuskelisteSublistDepositoryManager()
    }

}