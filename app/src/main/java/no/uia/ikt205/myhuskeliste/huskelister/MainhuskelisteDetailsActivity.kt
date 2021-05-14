package no.uia.ikt205.myhuskeliste.huskelister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import no.uia.ikt205.myhuskeliste.EXTRA_HUSKELISTE_INFO
import no.uia.ikt205.myhuskeliste.HuskelisteHolder
import no.uia.ikt205.myhuskeliste.huskelister.data.Huskeliste
import no.uia.ikt205.myhuskeliste.huskelister.data.Mainhuskeliste
import no.uia.ikt205.myhuskeliste.databinding.ActivityHuskelisteDetailsBinding

class MainhuskelisteDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHuskelisteDetailsBinding
    private lateinit var mainhuskeliste: Mainhuskeliste

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHuskelisteDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val receivedHuskeliste = HuskelisteHolder.PickedHuskeliste

        binding.huskelisteRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.huskelisteRecyclerView.adapter = HuskelisteSublistCollectionAdapter(emptyList<Huskeliste>())


        HuskelisteSublistDepositoryManager.instance.onTodos = {
            (binding.huskelisteRecyclerView.adapter as HuskelisteSublistCollectionAdapter).updateCollection(it)
        }

        HuskelisteSublistDepositoryManager.instance.load()

        binding.progressBarSub.max = HuskelisteSublistDepositoryManager.instance.elementSize()
        binding.progressBarSub.progress = HuskelisteSublistDepositoryManager.instance.doneElements()

        if(receivedHuskeliste != null){
            mainhuskeliste = receivedHuskeliste
            Log.i("Details view", receivedHuskeliste.toString())
        } else{

            setResult(RESULT_CANCELED, Intent(EXTRA_HUSKELISTE_INFO).apply {

            })

            finish()
        }

        binding.title.text = mainhuskeliste.title



    }
}