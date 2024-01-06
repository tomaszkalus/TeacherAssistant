package com.example.teacherassistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.model.StudyClass

class ClassListAdapter(private val dataList: Array<StudyClass>) :
    RecyclerView.Adapter<ClassListAdapter.ViewHolder>(){

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val btn = view.findViewById<Button>(R.id.button)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.class_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.btn.text = dataList[position].name
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}