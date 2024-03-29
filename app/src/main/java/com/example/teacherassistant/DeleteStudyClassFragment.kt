package com.example.teacherassistant

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.view.size
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.databinding.FragmentClassesListBinding
import com.example.teacherassistant.databinding.FragmentDeleteStudyClassBinding
import com.example.teacherassistant.viewmodel.StudyClassViewModel
import com.example.teacherassistant.viewmodel.StudyClassViewModelFactory

class DeleteStudyClassFragment : Fragment() {
    private var _binding: FragmentDeleteStudyClassBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DeleteClassListAdapter


    private val studyClassViewModel: StudyClassViewModel by viewModels {
        StudyClassViewModelFactory((activity?.application as TeacherAssistantApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteStudyClassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.deleteClassesRecyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        adapter = DeleteClassListAdapter()
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context, layoutManager.orientation
            )
        )



        studyClassViewModel.allStudyClasses.observe(viewLifecycleOwner) { studyClasses ->
            studyClasses?.let {
                adapter.submitList(it)
            }
        }

        binding.removeClassesButton.setOnClickListener {

            val studyClassesToDelete = mutableListOf<Int>()

            for (i in 0 until recyclerView.size) {
                Log.d("ClassesListFragment", "item $i: ${recyclerView.getChildAt(i)}")
                val checkBox = recyclerView.getChildAt(i).findViewById<CheckBox>(R.id.studyClassCheckbox)
                val checked = checkBox.isChecked
                if (checked) {
                    studyClassesToDelete.add(i)
                }
            }

            adapter.currentList.forEachIndexed { index, studyClass ->
                if (studyClassesToDelete.contains(index)) {
                    studyClassViewModel.delete(studyClass)
                }
            }
            view.findNavController().navigate(R.id.action_deleteStudyClassFragment_to_classesListFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("ClassesListFragment", "onCreate called")
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)



        Log.d("dbx", studyClassViewModel.allStudyClasses.value?.size.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}