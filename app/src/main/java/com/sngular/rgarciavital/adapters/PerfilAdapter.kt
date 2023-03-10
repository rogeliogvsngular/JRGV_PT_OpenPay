package com.sngular.rgarciavital.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sngular.rgarciavital.data.model.Review
import com.sngular.rgarciavital.data.model.ReviewResult
import com.sngular.rgarciavital.databinding.ItemReviewBinding

class PerfilAdapter(val context: Context, val recyclerViewHome: RecyclerViewHomeClickListener) : RecyclerView.Adapter<ViewHolder>(){
    private lateinit var recyclerView: RecyclerView
    lateinit var mActivity: AppCompatActivity

    var items: List<Review> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemReviewBinding.inflate(
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

        /*holder.itemView.setOnClickListener {
            recyclerViewHome.clickOnItem(
                item!!,
                holder.itemView
            )
        }*/
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

    fun submitList(itemList: List<Review>){
        items = itemList!!
        notifyDataSetChanged()
    }

    private fun isLinearLayoutManager() = recyclerView.layoutManager is LinearLayoutManager
}

class ViewHolder(private val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemperfil: Review, isLinearLayoutManager: Boolean) {
        binding.apply {
            itemreview = itemperfil
            executePendingBindings()
        }
    }
}
interface RecyclerViewHomeClickListener {
    fun clickOnItem(data: Review, card: View)
}
