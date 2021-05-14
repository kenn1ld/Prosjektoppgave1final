package no.uia.ikt205.myhuskeliste.huskelister

import android.content.Context
import no.uia.ikt205.myhuskeliste.huskelister.data.Huskeliste

class HuskelisteSublistDepositoryManager {
    private lateinit var todosCollection : MutableList<Huskeliste>

    var onTodos: ((List<Huskeliste>) -> Unit)? = null
    var onTodosUpdate: ((huskeliste: Huskeliste) -> Unit)? = null

    fun load(url: String = ""){
        todosCollection= mutableListOf(
                Huskeliste("Egg", true),
                Huskeliste("Melk", true),
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

    fun elementSize():Int{
        return todosCollection.size
    }

    fun doneElements():Int{
        return todosCollection.filter { it -> it.checkBox }.size
    }

    companion object {
        val instance = HuskelisteSublistDepositoryManager()
    }

}