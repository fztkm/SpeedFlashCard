package com.fztkm.card.speedflashcard.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fztkm.card.speedflashcard.database.Quiz

class AddQuizGroupViewModel : ViewModel() {
    private val _quizzes = MutableLiveData<List<Quiz>>()
    val quizzes: LiveData<List<Quiz>>
        get() = _quizzes
    
    init {
        _quizzes.value = listOf(Quiz("", "", ""))
    }
}