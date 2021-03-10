package no.uia.ikt205.mybooks.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import no.uia.ikt205.mybooks.books.data.Book
import no.uia.ikt205.mybooks.databinding.BookLayoutBinding


class BookCollectionAdapter(private val books:MutableList<Book>, private val onBookClicked:(Book) -> Unit) : RecyclerView.Adapter<BookCollectionAdapter.ViewHolder>(){

    class ViewHolder(val binding:BookLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.author.text = book.author
            binding.title.text = book.title
            binding.published.text = book.published.toString()
        }
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(BookLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    public fun updateCollection(newBooks:List<Book>){
        books.clear()
        books.addAll(newBooks)
        notifyDataSetChanged()
    }


}