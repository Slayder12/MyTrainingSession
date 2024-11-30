package com.example.mytrainingsession

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class StartActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var transitionBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        init()

        transitionBTN.setOnClickListener{
            val intent = Intent(this, ExerciseListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun init() {
        toolbar = findViewById(R.id.toolbar)
        title = ""
        setSupportActionBar(toolbar)

        transitionBTN = findViewById(R.id.transitionBTN)
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