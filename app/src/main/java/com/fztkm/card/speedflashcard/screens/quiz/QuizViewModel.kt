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

    //クイズを間違えたときに何問戻るか
    private val _backWidthOnFail = MutableLiveData<Int>()
    val backWidthOnFail: LiveData<Int>
        get() = _backWidthOnFail

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

    private val _eventFinishQuiz = MutableLiveData<Boolean>()
    val eventFinishQuiz: LiveData<Boolean>
        get() = _eventFinishQuiz

    private val _restartNum = MutableLiveData<Int>()
    val restartNum: LiveData<Int>
        get() = _restartNum

    private val _elapsedTime = MutableLiveData<Long>()
    val elapsedTime: LiveData<Long>
        get() = _elapsedTime

    init {
        initState()
    }

    private fun initState() {
        _isReverseCard.value = false
        _selectedQuizGroup.value = quizGroup
        _quizzes.value = _selectedQuizGroup.value!!.quizzes
        _eventFinishQuiz.value = false
        _currentQuizIndex.value = 0
        _backWidthOnFail.value = 1
        _restartNum.value = 0
    }

    fun setBackWidth(num: Int) {
        _backWidthOnFail.value = num
    }

    fun startQuiz() {
        _currentQuizIndex.value = 0
    }

    fun onNextQuiz() {
        currentQuizIndex.value ?: run { _currentQuizIndex.value = 0 }
        _currentQuizIndex.value = _currentQuizIndex.value!! + 1
        checkQuizFinished()
    }

    fun onFailedQuiz() {
        _restartNum.value = restartNum.value!! + 1
        currentQuizIndex.value ?: run { _currentQuizIndex.value = 0 }
        backWidthOnFail.value ?: return
        if (currentQuizIndex.value!! > backWidthOnFail.value!!) {
            _currentQuizIndex.value = currentQuizIndex.value!! - backWidthOnFail.value!!
        } else {
            _currentQuizIndex.value = 0
        }
    }

    fun checkQuizFinished() {
        currentQuizIndex.value ?: return
        quizzes.value ?: return
        if (currentQuizIndex.value!! >= quizzes.value!!.size - 1) {
            _eventFinishQuiz.value = true
        }
    }

    fun onQuizFinishedCompleted() {
        _eventFinishQuiz.value = false
    }
    
    fun onReplay() {
        initState()
    }
}