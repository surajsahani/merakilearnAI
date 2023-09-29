package com.androidhacks.merakiliteai


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidhacks.merakiliteai.models.CourseData

class CourseAdapter(
    private val sportsList: List<CourseData>,
    private val listener: CourseAdapterListener
) : RecyclerView.Adapter<CourseAdapter.SearchViewHolder>() {

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.search_title_text_view)
        val iconImageView: ImageView = itemView.findViewById(R.id.search_icon_image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_search, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val sports: CourseData = sportsList[position]
        holder.titleTextView.text = sports.title
        holder.iconImageView.setImageResource(sports.image)

        holder.itemView.setOnClickListener {
            listener.onSportSelected(sports)
        }
    }

    override fun getItemCount() = sportsList.size

    interface CourseAdapterListener {
        fun onSportSelected(sports: CourseData?)
    }
}