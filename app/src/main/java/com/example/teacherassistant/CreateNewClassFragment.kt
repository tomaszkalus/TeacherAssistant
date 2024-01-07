package com.example.teacherassistant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.teacherassistant.databinding.FragmentClassesListBinding
import com.example.teacherassistant.databinding.FragmentCreateNewClassBinding
import com.example.teacherassistant.model.StudyClass
import com.example.teacherassistant.viewmodel.StudyClassViewModel
import com.example.teacherassistant.viewmodel.StudyClassViewModelFactory

class CreateNewClassFragment : Fragment() {
    private var _binding: FragmentCreateNewClassBinding? = null
    private val binding get() = _binding!!

    private val studyClassViewModel: StudyClassViewModel by viewModels {
        StudyClassViewModelFactory((activity?.application as TeacherAssistantApplication).repository)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNewClassButton.setOnClickListener {

            val newClassName = binding.classNameTextInput.text.toString()

            if(newClassName.isEmpty()){
                Toast.makeText(context, "Nazwa grupy nie może być pusta", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newStudyClass = StudyClass(newClassName)
            studyClassViewModel.insert(newStudyClass)

            val action = CreateNewClassFragmentDirections.actionCreateNewClassFragmentToClassesListFragment()
            view.findNavController().navigate(action)
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
//        return inflater.inflate(R.layout.fragment_create_new_class, container, false)
        _binding = FragmentCreateNewClassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}