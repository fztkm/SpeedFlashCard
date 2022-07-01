package com.fztkm.card.speedflashcard.screens.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fztkm.card.speedflashcard.database.Quiz
import com.fztkm.card.speedflashcard.database.QuizGroup

class QuizViewModel(val quizGroup: QuizGroup) : ViewModel() {

    //TODO add dataSource via constructor
    //val database = dataSource

    //TODO get from database
    private val _quizzez = MutableLiveData<List<Quiz>>()
    val quizzez: LiveData<List<Quiz>>
        get() = _quizzez


}