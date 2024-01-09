package com.example.teacherassistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.model.StudyClass

class DeleteClassListAdapter :
    ListAdapter<StudyClass, DeleteClassListAdapter.DeleteStudyClassViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeleteStudyClassViewHolder {
        return DeleteStudyClassViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DeleteStudyClassViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)

    }

    class DeleteStudyClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val studyClassItemView: TextView = itemView.findViewById(R.id.studyClassCheckbox)

        fun bind(text: String?) {
            studyClassItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): DeleteStudyClassViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.class_list_delete_item, parent, false)
                return DeleteStudyClassViewHolder(view)
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