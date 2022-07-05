package com.fztkm.card.speedflashcard.screens.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fztkm.card.speedflashcard.database.QuizGroup
import com.fztkm.card.speedflashcard.databinding.ActivityQuizBinding
import com.fztkm.card.speedflashcard.screens.home.HomeFragment

class QuizActivity : AppCompatActivity() {
    lateinit var binding: ActivityQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        val quizGroup = intent.getParcelableExtra<QuizGroup>(HomeFragment.QUIZ_GROUP_KEY)
        //val quizGroup = QuizGroup(1, "エディ", listOf<Quiz>(), "x")
        quizGroup?.let {
            val viewModelFactory = QuizViewModelFactory(quizGroup)
            val viewModel = ViewModelProvider(this, viewModelFactory).get(
                QuizViewModel::class.java
            )
        }
        setContentView(binding.root)
    }
}