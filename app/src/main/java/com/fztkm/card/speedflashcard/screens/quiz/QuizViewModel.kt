package com.fztkm.card.speedflashcard.screens.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fztkm.card.speedflashcard.database.Quiz
import com.fztkm.card.speedflashcard.database.QuizGroup

class QuizViewModel(val quizGroup: QuizGroup) : ViewModel() {

    private val _selectedQuizGroup = MutableLiveData<QuizGroup>()
    val selectedQuizGroup: LiveData<QuizGroup>
        get() = _selectedQuizGroup

    //TODO add dataSource via constructor
    //val database = dataSource

    //TODO get from database
    private val _quizzes = MutableLiveData<List<Quiz>>()
    val quizzes: LiveData<List<Quiz>>
        get() = _quizzes

    private val _currentQuizIndex = MutableLiveData<Int>()
    val currentQuizIndex: LiveData<Int>
        get() = _currentQuizIndex

    //問題と答えを逆にするかどうか（単語帳をひっくり返して使う場合true）
    private val _isReverseCard = MutableLiveData<Boolean>()
    val isReverseCard: LiveData<Boolean>
        get() = _isReverseCard

    //表示したい問題文
    var questionStrDisplay = Transformations.map(_currentQuizIndex) { index ->
        quizzes.value?.let {
            if (isReverseCard.value != true) it[index].question
            else it[index].answer
        }
    }

    //表示したい答え
    val answerStrDisplay = Transformations.map(currentQuizIndex) { index ->
        quizzes.value?.let {
            if (isReverseCard.value != true) it[index].answer
            else it[index].question
        }
    }

    private val _restartNum = MutableLiveData<Int>()
    val restartNum: LiveData<Int>
        get() = _restartNum

    private val _elapsedTime = MutableLiveData<Long>()
    val elapsedTime: LiveData<Long>
        get() = _elapsedTime

    init {
        _isReverseCard.value = false
        _selectedQuizGroup.value = quizGroup
        _quizzes.value = _selectedQuizGroup.value!!.quizzes
        _currentQuizIndex.value = 0
    }

    fun startQuiz() {
        _currentQuizIndex.value = 0
    }
}