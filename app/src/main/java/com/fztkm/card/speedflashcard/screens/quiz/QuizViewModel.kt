package com.fztkm.card.speedflashcard.screens.quiz

import android.os.CountDownTimer
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

    //全ての問題を解くのに経過している時間
    private val _elapsedTime = MutableLiveData<Long>()
    val elapsedTime: LiveData<Long>
        get() = _elapsedTime


    private var _solveTimer = MutableLiveData<CountDownTimer?>()

    private val _solveRemainTime = MutableLiveData<Int>()
    val solveRemainTime: LiveData<Int>
        get() = _solveRemainTime

    //制限時間
    private val _solveTimerDuration = MutableLiveData<Int>()
    val solveTimerDuration: LiveData<Int>
        get() = _solveTimerDuration

    private val _eventTimeUp = MutableLiveData<Boolean>()
    val eventTimeUp: LiveData<Boolean>
        get() = _eventTimeUp

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
        _solveTimerDuration.value = 5000
        _solveRemainTime.value = _solveTimerDuration.value!! + 1
        _eventTimeUp.value = false
    }

    fun setBackWidth(num: Int) {
        _backWidthOnFail.value = num
    }

    fun startQuiz() {
        _currentQuizIndex.value = 0
        setUpTimer()
    }

    fun onNextQuiz() {
        if (!checkQuizFinished()) {
            currentQuizIndex.value ?: run { _currentQuizIndex.value = 0 }
            _currentQuizIndex.value = _currentQuizIndex.value!!.plus(1)
            setUpTimer()
        }
    }

    fun onFailedQuiz() {
        _restartNum.value = restartNum.value!! + 1
        currentQuizIndex.value ?: run { _currentQuizIndex.value = 0 }
        backWidthOnFail.value ?: return
        if (currentQuizIndex.value!! > backWidthOnFail.value!!) {
            _currentQuizIndex.value = _currentQuizIndex.value!!.minus(backWidthOnFail.value!!)
        } else {
            _currentQuizIndex.value = 0
        }
        setUpTimer()
    }

    private fun checkQuizFinished(): Boolean {
        currentQuizIndex.value ?: return false
        quizzes.value ?: return false
        if (currentQuizIndex.value!! == quizzes.value!!.size - 1) {
            _eventFinishQuiz.value = true
            return true
        }
        return false
    }

    fun onQuizFinishedCompleted() {
        _eventFinishQuiz.value = false
    }

    fun onReplay() {
        initState()
        setUpTimer()
    }

    private fun setUpTimer() {
        _solveTimer.value?.let {
            cancelTimer()
        }

        _solveRemainTime.value = _solveTimerDuration.value!! + 1
        _solveTimerDuration.value ?: return
        _solveTimer.value = object : CountDownTimer(
            _solveTimerDuration.value!! * 1L, 1L
        ) {
            override fun onTick(millisUntilFinished: Long) {
                _solveRemainTime.value = millisUntilFinished.toInt()
            }

            override fun onFinish() {
                _solveRemainTime.value = 0
                cancelTimer()
                navigateTimeUp()
            }
        }
        _solveTimer.value?.start()
    }

    private fun cancelTimer() {
        _solveTimer.value?.cancel()
        _solveTimer.value = null
    }

    private fun navigateTimeUp() {
        _eventTimeUp.value = true
    }

    fun onNavigateTimeUpCompleted() {
        _eventTimeUp.value = false
    }

}