package no.uia.ikt205.mybooks

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import no.uia.ikt205.mybooks.books.data.Book
import no.uia.ikt205.mybooks.books.BookCollectionAdapter
import no.uia.ikt205.mybooks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var bookColection:MutableList<Book> = mutableListOf(
            Book("Martha Wells", "All systems red", 2017),
            Book("Neil Gaiman", "American Gods",2011),
            Book("Terry Pratchett", "The wee free men", 2003)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bookListing.layoutManager = LinearLayoutManager(this)
        binding.bookListing.adapter = BookCollectionAdapter(bookColection, this::onBookClicked)

        binding.saveBt.setOnClickListener {

            // Fortsatt ikke bra, men bedre :)

            val author = binding.author.text.toString()
            val title = binding.title.text.toString()
            val published =  binding.published.text.toString().toInt()

            binding.author.setText("")
            binding.title.setText("")
            binding.published.setText("")

            addBook(title,author,published)

            val ipm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            ipm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        }
    }

    private fun addBook(title:String, author:String, published:Int){

        val book = Book(title,author,published)
        bookColection.add(book)

    }



    private fun onBookClicked(book: Book):Unit{
        // Vis detalje side for bok.
    }


}