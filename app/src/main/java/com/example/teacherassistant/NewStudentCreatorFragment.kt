package com.example.teacherassistant

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.databinding.FragmentNewStudentCreatorBinding
import com.example.teacherassistant.model.Student
import com.example.teacherassistant.model.StudyClass
import com.example.teacherassistant.viewmodel.NewStudentViewModel
import com.example.teacherassistant.viewmodel.NewStudentViewModelFactory

class NewStudentCreatorFragment : Fragment() {
    private var _binding: FragmentNewStudentCreatorBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ClassToStudentAssignmentListAdapter

    private val studentViewModel: NewStudentViewModel by viewModels {
        NewStudentViewModelFactory(
            (activity?.application as TeacherAssistantApplication).studentRepository,
            (activity?.application as TeacherAssistantApplication).repository
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNewStudentButton.setOnClickListener {

            val newStudentFirstName = binding.studentFirstNameTextInput.text.toString()
            val newStudentLastName = binding.studentLastNameTextInput.text.toString()

            if (newStudentFirstName.isEmpty() || newStudentLastName.isEmpty()) {
                Toast.makeText(context, "Pola nie mogą być pusta", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            val studentStudyClassesIds = mutableListOf<Int>()
//            val studentStudyClasses = mutableListOf<StudyClass>()
//
//            for (i in 0 until recyclerView.size) {
//                val checkBox = recyclerView.getChildAt(i).findViewById<CheckBox>(R.id.studyClassCheckbox)
//                val checked = checkBox.isChecked
//                if (checked) {
//                    studentStudyClasses.add(adapter.currentList[i])
//                }
//            }

            // TODO add student with study classes to db

            val newStudent = Student(newStudentFirstName, newStudentLastName)
            studentViewModel.insert(newStudent)


            val action =
                NewStudentCreatorFragmentDirections.actionNewStudentCreatorFragmentToAllStudentsListFragment()
            view.findNavController().navigate(action)
        }

        recyclerView = binding.classesRecyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        adapter = ClassToStudentAssignmentListAdapter()
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context, layoutManager.orientation
            )
        )

        studentViewModel.allStudyClasses.observe(viewLifecycleOwner) { studyClasses ->
            studyClasses?.let {
                adapter.submitList(it)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewStudentCreatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}