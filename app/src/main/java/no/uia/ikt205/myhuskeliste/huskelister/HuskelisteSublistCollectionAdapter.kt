package no.uia.ikt205.myhuskeliste.huskelister

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.elemt_layout.view.*
import no.uia.ikt205.myhuskeliste.databinding.ElemtLayoutBinding
import no.uia.ikt205.myhuskeliste.huskelister.data.Huskeliste


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