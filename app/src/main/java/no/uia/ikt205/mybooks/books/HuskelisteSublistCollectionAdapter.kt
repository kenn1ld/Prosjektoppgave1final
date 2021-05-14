package no.uia.ikt205.mybooks.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_book_details.view.*
import kotlinx.android.synthetic.main.activity_book_details.view.title
import kotlinx.android.synthetic.main.book_layout.view.*
import kotlinx.android.synthetic.main.elemt_layout.view.*
import no.uia.ikt205.mybooks.books.data.Book
import no.uia.ikt205.mybooks.books.data.Huskeliste
import no.uia.ikt205.mybooks.databinding.ActivityBookDetailsBinding
import no.uia.ikt205.mybooks.databinding.BookLayoutBinding
import no.uia.ikt205.mybooks.databinding.ElemtLayoutBinding


class HuskelisteSublistCollectionAdapter(private var huskeliste:List<Huskeliste>):RecyclerView.Adapter<HuskelisteSublistCollectionAdapter.ViewHolder>(){

    class ViewHolder(val binding: ElemtLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(huskeliste: Huskeliste) {
            binding.title.text = huskeliste.title
        }
    }

    fun getDoneElements():Int{
        var completed:Int = 0

       // huskeliste.filter { it -> it.checkBox }

        for (element in huskeliste){
            if(element.checkBox){
                completed++
            }
        }
        return completed
    }

    override fun getItemCount(): Int = huskeliste.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ElemtLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val huskeliste = huskeliste[position]
        holder.bind(huskeliste)

        holder.itemView.apply {

            title.text = huskeliste.title

            deleteButton.setOnClickListener {
                val removeElement = Huskeliste(title.text as String)
                HuskelisteSublistDepositoryManager.instance.removeElement(removeElement)
            }
        }
    }

    fun updateCollection(newHuskeliste:List<Huskeliste>){
        huskeliste = newHuskeliste
        notifyDataSetChanged()
    }



}