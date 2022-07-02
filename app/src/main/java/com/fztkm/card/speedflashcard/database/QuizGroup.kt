package com.fztkm.card.speedflashcard.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuizGroup(
    val id: Int,
    val name: String,
    val quizzes: List<Quiz>,
    val genre: String,
) : Parcelable {
    val length: Int
        get() {
            return quizzes.size
        }
}
