package com.example.bai3

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var students: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        students = listOf(
            Student("Nguyen Van A", "123456"),
            Student("Tran Thi B", "654321"),
            Student("Le Van C", "112233"),
        )

        studentAdapter = StudentAdapter(students)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredStudents = if (!newText.isNullOrEmpty() && newText.length > 2) {
                    students.filter {
                        it.name.contains(newText, ignoreCase = true) ||
                                it.mssv.contains(newText)
                    }
                } else {
                    students
                }
                studentAdapter.updateList(filteredStudents)
                return true
            }
        })
    }
}
