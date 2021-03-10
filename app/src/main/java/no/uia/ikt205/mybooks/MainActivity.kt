package no.uia.ikt205.mybooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            // Dette er fort og galt. Vis mer ansvar selv ;)
            // Det er en bug som må fikses her.
            bookColection.add(Book(binding.author.text.toString(), binding.title.text.toString(), binding.published.text.toString().toInt()))
            (binding.bookListing.adapter as BookCollectionAdapter).updateCollection(bookColection)
        }
    }

    private fun onBookClicked(book: Book):Unit{
        // Vis detalje side for bok.
    }


}