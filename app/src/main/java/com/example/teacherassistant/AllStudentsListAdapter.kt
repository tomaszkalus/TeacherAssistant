package com.example.teacherassistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.model.Student

class AllStudentsListAdapter :
    ListAdapter<Student, AllStudentsListAdapter.StudentViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind("${current.firstName} ${current.lastName}")
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val studentItemView: TextView = itemView.findViewById(R.id.studentNameTextView)

        fun bind(text: String?) {
            studentItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): StudentViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.student_list_item, parent, false)
                return StudentViewHolder(view)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.firstName == newItem.firstName && oldItem.lastName == newItem.lastName
            }
        }
    }
}