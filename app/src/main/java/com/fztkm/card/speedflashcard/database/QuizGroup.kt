package com.fztkm.card.speedflashcard.database

data class QuizGroup(
    val name: String,
    val quizzes: List<Quiz>,
    val genre: String
)