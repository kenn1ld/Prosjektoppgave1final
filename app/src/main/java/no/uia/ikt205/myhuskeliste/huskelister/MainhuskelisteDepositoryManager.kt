package no.uia.ikt205.myhuskeliste.huskelister

import android.content.Context
import com.android.volley.RequestQueue
import no.uia.ikt205.myhuskeliste.huskelister.data.Mainhuskeliste

class MainhuskelisteDepositoryManager {

    private lateinit var huskelisteCollection: MutableList<Mainhuskeliste>
    private lateinit var queue: RequestQueue

    var onMainhuskeliste: ((List<Mainhuskeliste>) -> Unit)? = null
    var onMainhuskelisteUpdate: ((mainhuskeliste: Mainhuskeliste) -> Unit)? = null


    fun load(url: String, context: Context) {



        huskelisteCollection = mutableListOf(
                Mainhuskeliste("Mat å spise"),
                Mainhuskeliste("Filmer å se"),
                Mainhuskeliste("Spill å spille")
        )

        onMainhuskeliste?.invoke(huskelisteCollection)
    }

    fun updateHuskeliste(mainhuskeliste: Mainhuskeliste) {

        onMainhuskelisteUpdate?.invoke(mainhuskeliste)
    }

    fun addHuskeliste(mainhuskeliste: Mainhuskeliste) {
        huskelisteCollection.add(mainhuskeliste)
        onMainhuskeliste?.invoke(huskelisteCollection)
    }

    fun removeHuskeliste(mainhuskeliste: Mainhuskeliste) {
        huskelisteCollection.remove(mainhuskeliste)
        onMainhuskeliste?.invoke(huskelisteCollection)
    }

    companion object {
        val instance = MainhuskelisteDepositoryManager()
    }

}