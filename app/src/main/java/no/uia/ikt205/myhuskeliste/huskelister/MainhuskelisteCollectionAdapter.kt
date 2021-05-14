package no.uia.ikt205.myhuskeliste.huskelister

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.huskeliste_main_layout.view.*
import no.uia.ikt205.myhuskeliste.huskelister.data.Mainhuskeliste
import no.uia.ikt205.myhuskeliste.databinding.HuskelisteMainLayoutBinding


class MainhuskelisteCollectionAdapter(private var mainhuskelister:List<Mainhuskeliste>, private val onMainhuskelisteClicked:(Mainhuskeliste) -> Unit) : RecyclerView.Adapter<MainhuskelisteCollectionAdapter.ViewHolder>(){

    class ViewHolder(val binding:HuskelisteMainLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(mainhuskeliste: Mainhuskeliste, onMainhuskelisteClicked:(Mainhuskeliste) -> Unit) {
            binding.title.text = mainhuskeliste.title

            binding.card.setOnClickListener {
                onMainhuskelisteClicked(mainhuskeliste)
            }
        }
    }

    override fun getItemCount(): Int = mainhuskelister.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mainhuskeliste = mainhuskelister[position]
        holder.bind(mainhuskeliste,onMainhuskelisteClicked)

        holder.itemView.apply {

            title.text = mainhuskeliste.title

            deleteBt.setOnClickListener {
                val remove = Mainhuskeliste(title.text as String)
                MainhuskelisteDepositoryManager.instance.removeHuskeliste(remove)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(HuskelisteMainLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    fun updateCollection(newMainhuskelister:List<Mainhuskeliste>){
        mainhuskelister = newMainhuskelister
        notifyDataSetChanged()
    }


}