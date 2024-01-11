package com.example.teacherassistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.model.StudyClass

class ClassToStudentAssignmentListAdapter :
    ListAdapter<StudyClass, ClassToStudentAssignmentListAdapter.ClassToStudentAssignmentViewHolder>(
        WORDS_COMPARATOR
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClassToStudentAssignmentViewHolder {
        return ClassToStudentAssignmentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ClassToStudentAssignmentViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)

    }

    class ClassToStudentAssignmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val studyClassItemView: TextView = itemView.findViewById(R.id.studyClassCheckbox)

        fun bind(text: String?) {
            studyClassItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): ClassToStudentAssignmentViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.class_to_student_assignment_list_item, parent, false)
                return ClassToStudentAssignmentViewHolder(view)
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