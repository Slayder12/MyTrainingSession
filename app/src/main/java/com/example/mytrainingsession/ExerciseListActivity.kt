package com.example.mytrainingsession

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ExerciseListActivity : AppCompatActivity() {

    private val exercise = ExerciseDataBase.exercise
    private lateinit var listViewLV: ListView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_list)

        toolbar = findViewById(R.id.toolbar)
        title = ""
        setSupportActionBar(toolbar)
        listViewLV = findViewById(R.id.listViewLV)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, exercise)
        listViewLV.adapter = adapter

        listViewLV.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, exerciseIndex, _ ->
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("index", exerciseIndex)
                startActivity(intent)
            }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.exitMenu) {
            Toast.makeText(this, "Программа завершена", Toast.LENGTH_SHORT).show()
            finishAffinity()
        }
        return super.onOptionsItemSelected(item)
    }
}