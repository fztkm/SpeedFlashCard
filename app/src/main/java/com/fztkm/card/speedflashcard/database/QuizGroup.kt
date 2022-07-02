package com.fztkm.card.speedflashcard.database

data class QuizGroup(
    val id: Int,
    val name: String,
    val quizzes: List<Quiz>,
    val genre: String,
) {
    val length: Int
        get() {
            return quizzes.size
        }
}
