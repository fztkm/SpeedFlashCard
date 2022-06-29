package com.fztkm.card.speedflashcard.database

object QuizDatabase {
    private val quizGroupList = mutableListOf<QuizGroup>()

    init {
        quizGroupList.add(
            QuizGroup(
                "Test 1", listOf(
                    Quiz("1 + 1 ", "2", "add"),
                    Quiz("abcd", "efg", "abdcefg"),
                    Quiz("元号は", "令和", "平成の次")
                ), "first"
            )
        )
        quizGroupList.add(
            QuizGroup(
                "Test 2", listOf(
                    Quiz("1 + 20 ", "21", "add"),
                    Quiz("abcdefg", "hij", "abdcefg"),
                    Quiz("元号は", "令和です", "平成の次")
                ), "first"
            )
        )
        quizGroupList.add(
            QuizGroup(
                "Test 3", listOf(
                    Quiz("30 + 1 ", "31", "add"),
                    Quiz("abc", "defg", "abdcefg"),
                    Quiz("元号は", "令和", "平成の次")
                ), "first"
            )
        )
    }

    fun getAllQuiz(): List<QuizGroup> {
        return quizGroupList
    }
}
