package com.example.teacherassistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.model.StudyClass

class ClassListAdapterNew :
    ListAdapter<StudyClass, ClassListAdapterNew.StudyClassViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyClassViewHolder {
        return StudyClassViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: StudyClassViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class StudyClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val studyClassItemView : TextView = itemView.findViewById(R.id.button)

        fun bind(text: String?) {
            studyClassItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): StudyClassViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.class_list_item, parent, false)
                return StudyClassViewHolder(view)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<StudyClass>() {
            override fun areItemsTheSame(oldItem: StudyClass, newItem: StudyClass): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: StudyClass, newItem: StudyClass): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}