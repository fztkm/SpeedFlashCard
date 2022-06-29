package com.fztkm.card.speedflashcard.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fztkm.card.speedflashcard.database.QuizDatabase
import com.fztkm.card.speedflashcard.database.QuizGroup

class HomeViewModel : ViewModel() {
    private val _quizGroups = MutableLiveData<List<QuizGroup>>()
    val quizGroups: LiveData<List<QuizGroup>>
        get() = _quizGroups

    init {
        _quizGroups.value = QuizDatabase.getAllQuiz()
    }
}