package no.uia.ikt205.mybooks.books

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import no.uia.ikt205.mybooks.BookHolder
import no.uia.ikt205.mybooks.EXTRA_BOOK_INFO
import no.uia.ikt205.mybooks.books.data.Book
import no.uia.ikt205.mybooks.books.data.Huskeliste
import no.uia.ikt205.mybooks.databinding.ActivityBookDetailsBinding
import no.uia.ikt205.mybooks.databinding.ElemtLayoutBinding

class BookDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDetailsBinding
    private lateinit var book:Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val receivedBook = intent.getParcelableExtra<Book>(EXTRA_BOOK_INFO)
        val receivedBook = BookHolder.PickedBook

        binding.huskelisteRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.huskelisteRecyclerView.adapter = HuskelisteSublistCollectionAdapter(emptyList<Huskeliste>())


        HuskelisteSublistDepositoryManager.instance.onTodos = {
            (binding.huskelisteRecyclerView.adapter as HuskelisteSublistCollectionAdapter).updateCollection(it)
        }

        HuskelisteSublistDepositoryManager.instance.load()

        binding.progressBarSub.max = HuskelisteSublistDepositoryManager.instance.elementSize()
        binding.progressBarSub.progress = HuskelisteSublistDepositoryManager.instance.doneElements()

        if(receivedBook != null){
            book = receivedBook
            Log.i("Details view", receivedBook.toString())
        } else{

            setResult(RESULT_CANCELED, Intent(EXTRA_BOOK_INFO).apply {
                // legg til info vi vil sende tilbake til Main
            })

            finish()
        }

        binding.title.text = book.title



    }
}