package com.example.teacherassistant

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.databinding.FragmentClassesListBinding
import com.example.teacherassistant.viewmodel.StudyClassViewModel
import com.example.teacherassistant.viewmodel.StudyClassViewModelFactory

class ClassesListFragment : Fragment() {
    private var _binding: FragmentClassesListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ClassListAdapter

    private val studyClassViewModel: StudyClassViewModel by viewModels {
        StudyClassViewModelFactory((activity?.application as TeacherAssistantApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClassesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.classesRecyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        adapter = ClassListAdapter()
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context, layoutManager.orientation
            )
        )

        studyClassViewModel.allStudyClasses.observe(viewLifecycleOwner) { studyClasses ->
            studyClasses?.let {
                Log.d("dbx", "Study Classes Size: ${it.size}")
                adapter.submitList(it)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("ClassesListFragment", "onCreate called")
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        view?.findViewById<Button>(R.id.add_class_button)?.setOnClickListener {
            val action =
                ClassesListFragmentDirections.actionClassesListFragmentToCreateNewClassFragment()
        }

        Log.d("dbx", studyClassViewModel.allStudyClasses.value?.size.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}