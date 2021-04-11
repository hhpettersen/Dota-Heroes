package com.app.dotaheroes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.dotaheroes.R
import com.app.dotaheroes.models.HeroStats
import kotlinx.android.synthetic.main.hero_row.view.*

class HeroAdapter: RecyclerView.Adapter<HeroAdapter.HeroViewHolder>(){

    inner class HeroViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    private val diffCallback = object : DiffUtil.ItemCallback<HeroStats>(){
        override fun areItemsTheSame(oldItem: HeroStats, newItem: HeroStats): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HeroStats, newItem: HeroStats): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list: List<HeroStats>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(
                R.layout.hero_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {

        val item = differ.currentList[position]

        holder.itemView.apply {
            heroName.text = "${item.localized_name}"

//            Glide.with(this)
//                .load(item.img)
//                .into(heroIcon)
        }

    }
}