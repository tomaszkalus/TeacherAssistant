package com.example.teacherassistant

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.databinding.FragmentClassesListBinding
import com.example.teacherassistant.viewmodel.StudyClassViewModel

class ClassesListFragment : Fragment() {
//    private val viewModel: StudyClassViewModel by viewModels()
    private var _binding: FragmentClassesListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    private val studyClassViewModel: StudyClassViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val adapter = ClassListAdapterNew()

        studyClassViewModel.allStudyClasses.observe(this, Observer { studyClasses ->
            studyClasses?.let { adapter.submitList(it) }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClassesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.classesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

//        val dataset = viewModel.allStudyClasses

//        val dataset = arrayOf(
//            "Aplikacje Android gr.1 środy",
//            "Inżynieria Oprogramowania gr. 3 wtorki",
//            "Programowanie gr. 1 poniedziałki",
//            "Elektrotechnika gr. 3 piątki")

//        val classListAdapter = ClassListAdapterNew(dataset)

//        recyclerView.adapter = classListAdapter

    }
}