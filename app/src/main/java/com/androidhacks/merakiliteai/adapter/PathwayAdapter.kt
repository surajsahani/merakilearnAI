package com.androidhacks.merakiliteai.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidhacks.merakiliteai.databinding.ItemPathwayBinding
import com.androidhacks.merakiliteai.local.PathwayEntity
import com.bumptech.glide.Glide

class PathwayAdapter (private val pathways : List<PathwayEntity>, private val onPathwayClick : (PathwayEntity) -> Unit) : RecyclerView.Adapter<PathwayAdapter.PathwayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PathwayViewHolder {
        val binding = ItemPathwayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PathwayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PathwayViewHolder, position: Int) {
        holder.bind(pathways[position])
    }

    override fun getItemCount(): Int {
        return pathways.size
    }

    inner class PathwayViewHolder(private val binding: ItemPathwayBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pathway: PathwayEntity) {
            binding.pathwayText.text = pathway.name
            Glide.with(binding.logo.context)
                .load(pathway.logo)
                .into(binding.logo)


            binding.root.setOnClickListener {
                onPathwayClick(pathway)
            }
        }
    }
}
