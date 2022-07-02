package com.fztkm.card.speedflashcard.screens.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fztkm.card.speedflashcard.database.Quiz
import com.fztkm.card.speedflashcard.database.QuizGroup

class QuizViewModel(val quizGroup: QuizGroup) : ViewModel() {

    private val _selectedQuizGroup = MutableLiveData<QuizGroup>()
    val selectedQuizGrop: LiveData<QuizGroup>
        get() = _selectedQuizGroup

    //TODO add dataSource via constructor
    //val database = dataSource

    //TODO get from database
    private
    val _quizzez = MutableLiveData<List<Quiz>>()
    val quizzez: LiveData<List<Quiz>>
        get() = _quizzez

    private val _currentQuizIndex = MutableLiveData<Int>()
    val currentQuizIndex: LiveData<Int>
        get() = _currentQuizIndex

    //問題と答えを逆にするかどうか（単語帳をひっくり返して使う場合true）
    private val _isReverseCard = MutableLiveData<Boolean>()
    val isReverseCard: LiveData<Boolean>
        get() = _isReverseCard

    //表示したい問題文
    val questionStrDisplay = Transformations.map(currentQuizIndex) {
        it?.let { index ->
            quizzez.value?.let { list ->
                if (isReverseCard.value != true) list[index].answer
                else list[index].question
            }
        }
    }

    //表示したい答え
    val answerStrDisplay = Transformations.map(currentQuizIndex) {
        it?.let { index ->
            quizzez.value?.let { list ->
                if (isReverseCard.value != true) list[index].question
                else list[index].answer
            }
        }
    }

    private val _restartNum = MutableLiveData<Int>()
    val restartNum: LiveData<Int>
        get() = _restartNum

    private val _elapsedTime = MutableLiveData<Long>()
    val elapsedTime: LiveData<Long>
        get() = _elapsedTime

    init {
        _selectedQuizGroup.value = quizGroup
    }
}