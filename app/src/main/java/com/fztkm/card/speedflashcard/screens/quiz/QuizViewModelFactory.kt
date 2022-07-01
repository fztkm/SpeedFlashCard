package com.fztkm.card.speedflashcard.screens.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fztkm.card.speedflashcard.database.QuizGroup

class QuizViewModelFactory(val quizGroup: QuizGroup) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            return QuizViewModel(quizGroup) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}