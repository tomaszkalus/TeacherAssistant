package com.example.teacherassistant

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.databinding.FragmentAllStudentsListBinding
import com.example.teacherassistant.viewmodel.StudentViewModel
import com.example.teacherassistant.viewmodel.StudentViewModelFactory


class AllStudentsListFragment : Fragment() {
    private var _binding: FragmentAllStudentsListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AllStudentsListAdapter

    private val studentViewModel: StudentViewModel by viewModels {
        StudentViewModelFactory((activity?.application as TeacherAssistantApplication).studentRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllStudentsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.allStudentsRecyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        adapter = AllStudentsListAdapter()
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context, layoutManager.orientation
            )
        )
        studentViewModel.allStudents.observe(viewLifecycleOwner) { students ->
            students?.let {
                Log.d("dbx", "Students Size: ${it.size}")
                adapter.submitList(it)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("ClassesListFragment", "onCreate called")
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}