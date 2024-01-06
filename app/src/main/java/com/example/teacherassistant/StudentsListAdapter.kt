package com.example.teacherassistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class StudentsListAdapter(private val dataList : Array<String>) :
    RecyclerView.Adapter<StudentsListAdapter.ViewHolder>() {

    class ViewHolder (val view : View) : RecyclerView.ViewHolder(view){
        val studentBtn = view.findViewById<Button>(R.id.button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.class_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.studentBtn.text = dataList[position]
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}