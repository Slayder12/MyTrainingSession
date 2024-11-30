package com.example.mytrainingsession

import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    val exercise = ExerciseDataBase.exercise
    private lateinit var toolbar: Toolbar
    private lateinit var titleTV: TextView
    private lateinit var startButtonBTN: Button
    private lateinit var exerciseTV: TextView
    private lateinit var descriptionTV: TextView
    private lateinit var timerTV: TextView
    private lateinit var continueBTN: Button
    private lateinit var imageViewIV: ImageView

    private var exerciseIndex = 0
    private lateinit var currentExercise: Exercise
    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        exerciseIndex = intent.getIntExtra("index", 0)


        startButtonBTN.setOnClickListener {
            startWorkout()
        }

        continueBTN.setOnClickListener {
            completeExercise()
        }

    }

    private fun completeExercise() {
        timer.cancel()
        continueBTN.isEnabled = false
        startNextExercise()
    }

    private fun startWorkout(){

        titleTV.text = "Начало тренировки"
        startButtonBTN.isEnabled = false
        startButtonBTN.text = "Процесс тренировки"
        startNextExercise()
    }

    private fun startNextExercise() {
        if (exerciseIndex < exercise.size) {
            currentExercise = exercise[exerciseIndex]
            exerciseTV.text = currentExercise.name
            descriptionTV.text = currentExercise.description
            imageViewIV.setImageResource(exercise[exerciseIndex].gifImage)
            timerTV.text = formatTime(currentExercise.durationInSeconds)
            timer = object : CountDownTimer(
                currentExercise.durationInSeconds * 1000L,
                1000
            ) {
                override fun onTick(millisUntilFinished: Long) {
                    timerTV.text = formatTime((millisUntilFinished / 1000).toInt())
                }

                override fun onFinish() {
                    timerTV.text = "Упражнение завершено"
                    imageViewIV.visibility = View.VISIBLE
                    continueBTN.isEnabled = true
                    imageViewIV.setImageResource(0)
                }
            }.start()
            exerciseIndex++
        } else {
            exerciseTV.text = "Тренировка завершена"
            descriptionTV.text = ""
            timerTV.text = ""
            continueBTN.isEnabled = false
            startButtonBTN.isEnabled = true
            startButtonBTN.text = "Начать снова"
            exerciseIndex = 0
        }
    }

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)

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

    private fun init() {
        toolbar = findViewById(R.id.toolbar)
        title = ""
        setSupportActionBar(toolbar)

        titleTV = findViewById(R.id.titleTV)
        startButtonBTN = findViewById(R.id.startButtonBTN)
        exerciseTV = findViewById(R.id.exerciseTV)
        descriptionTV = findViewById(R.id.descriptionTV)
        timerTV = findViewById(R.id.timerTV)
        continueBTN = findViewById(R.id.continueBTN)
        imageViewIV = findViewById(R.id.imageViewIV)

    }
}

