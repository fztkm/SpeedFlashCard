package com.fztkm.card.speedflashcard.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fztkm.card.speedflashcard.database.Quiz

class AddQuizGroupViewModel : ViewModel() {
    private val _quizzes = MutableLiveData<List<Quiz>>()
    val quizzes: LiveData<List<Quiz>>
        get() = _quizzes
    private val _haveQuiz = MutableLiveData<Boolean>()
    val haveQuiz: LiveData<Boolean>
        get() = _haveQuiz

    init {
        //空の
        _haveQuiz.value = false
        _quizzes.value = listOf(Quiz("", "", ""))
    }

    fun insertQuiz(quiz: Quiz, quizGroupId: Int) {
        //Insert　Database
        _haveQuiz.value = true
    }
}