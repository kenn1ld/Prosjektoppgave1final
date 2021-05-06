package no.uia.ikt205.mybooks.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_book_details.view.*
import kotlinx.android.synthetic.main.activity_book_details.view.title
import kotlinx.android.synthetic.main.book_layout.view.*
import no.uia.ikt205.mybooks.books.data.Book
import no.uia.ikt205.mybooks.databinding.BookLayoutBinding


class BookCollectionAdapter(private var books:List<Book>, private val onBookClicked:(Book) -> Unit) : RecyclerView.Adapter<BookCollectionAdapter.ViewHolder>(){

    class ViewHolder(val binding:BookLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book, onBookClicked:(Book) -> Unit) {
            binding.title.text = book.title

            binding.card.setOnClickListener {
                onBookClicked(book)
            }
        }
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book,onBookClicked)

        holder.itemView.apply {

            title.text = book.title

            deleteBt.setOnClickListener {
                val remove = Book(title.text as String)
                BookDepositoryManager.instance.removeBook(remove)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(BookLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    fun updateCollection(newBooks:List<Book>){
        books = newBooks
        notifyDataSetChanged()
    }


}