package com.example.teacherassistant

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.model.StudyClass
import com.example.teacherassistant.viewmodel.StudyClassViewModel
import com.example.teacherassistant.viewmodel.StudyClassViewModelFactory


class MainActivity : AppCompatActivity() {

    //    private lateinit var navController: NavController
    private val newStudyClassActivityRequestCode = 1
    private val studyClassViewModel: StudyClassViewModel by viewModels {
        StudyClassViewModelFactory((application as TeacherAssistantApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.classes_recycler_view)
        val adapter = ClassListAdapter()
        val layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context, layoutManager.orientation
            )
        )

        findViewById<Button>(R.id.add_class_button).setOnClickListener {
            val intent = Intent(this@MainActivity, NewStudyClass::class.java)
            startActivityForResult(intent, newStudyClassActivityRequestCode)
        }

        studyClassViewModel.allStudyClasses.observe(this) { studyClasses ->
            studyClasses.let { adapter.submitList(it) }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newStudyClassActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewStudyClass.EXTRA_REPLY)?.let { reply ->
                val studyClass = StudyClass(reply)
                studyClassViewModel.insert(studyClass)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }


//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}