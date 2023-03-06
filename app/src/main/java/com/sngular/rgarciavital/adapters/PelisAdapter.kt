package com.sngular.rgarciavital.view.peliculas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sngular.rgarciavital.data.model.Pelicula
import com.sngular.rgarciavital.databinding.ItemPeliculaBinding


class PelisAdapter(val context: Context, val recyclerViewHome: RecyclerViewHomeClickListener) : RecyclerView.Adapter<ViewHolder>(){
    private lateinit var recyclerView: RecyclerView
    lateinit var mActivity: AppCompatActivity

    private val TAG: String = "AppPTOpenPay"

    var items: List<Pelicula> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPeliculaBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items!!.get(position)
            item?.let {
                holder.apply {
                    bind(item, isLinearLayoutManager())
                    itemView.tag = item
                }
            }

            holder.itemView.setOnClickListener {
                recyclerViewHome.clickOnItem(
                    item!!,
                    holder.itemView
                )
            }


    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun getItemCount(): Int {
        if (items == null) {
            return 0
        } else {
         return items!!.size
        }
    }

    fun submitList(itemList: List<Pelicula>){
        items = itemList!!
        notifyDataSetChanged()
    }

    private fun isLinearLayoutManager() = recyclerView.layoutManager is LinearLayoutManager
}

class ViewHolder(private val binding: ItemPeliculaBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itempeli: Pelicula, isLinearLayoutManager: Boolean) {
        binding.apply {
            item = itempeli
             executePendingBindings()
        }
    }
}
interface RecyclerViewHomeClickListener {
    fun clickOnItem(data: Pelicula, card: View)
}
