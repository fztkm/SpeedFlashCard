package com.fztkm.card.speedflashcard.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Quiz(
    var question: String,
    var answer: String,
    var description: String,
) : Parcelable
