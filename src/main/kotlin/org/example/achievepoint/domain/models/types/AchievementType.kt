package org.example.achievepoint.domain.models.types

enum class AchievementType(val condition: (Int) -> Boolean) {
    BOOK_READ_1(condition = { count -> count >= 1 }),
    BOOK_READ_5(condition = { count -> count >= 5 }),
    BOOK_READ_10(condition = { count -> count >= 10 }),

    COMMENT_LEFT_1(condition = { count -> count >= 1 }),
    COMMENT_LEFT_5(condition = { count -> count >= 5 }),
    COMMENT_LEFT_10(condition = { count -> count >= 10 }),

    BAD_COMMENT_LEFT_1(condition = { count -> count >= 1 }),
    BAD_COMMENT_LEFT_5(condition = { count -> count >= 5 }),
    BAD_COMMENT_LEFT_10(condition = { count -> count >= 10 }),

    GOOD_COMMENT_LEFT_1(condition = { count -> count >= 1 }),
    GOOD_COMMENT_LEFT_5(condition = { count -> count >= 5 }),
    GOOD_COMMENT_LEFT_10(condition = { count -> count >= 10 }),

    MIN_READ_TIME_7(condition = { count -> count <= 7 }),
    MIN_READ_TIME_5(condition = { count -> count <= 5 }),
    MIN_READ_TIME_1(condition = { count -> count <= 1 });

    companion object {
        @JvmStatic
        val BOOK_READ_ACHIEVEMENTS = listOf(
            BOOK_READ_1,
            BOOK_READ_5,
            BOOK_READ_10,
        )

        @JvmStatic
        val MIN_READ_TIME_ACHIEVEMENTS = listOf(
            MIN_READ_TIME_7,
            MIN_READ_TIME_5,
            MIN_READ_TIME_1,
        )

        @JvmStatic
        val COMMENT_ACHIEVEMENTS = listOf(
            COMMENT_LEFT_1,
            COMMENT_LEFT_5,
            COMMENT_LEFT_10,
        )

        @JvmStatic
        val BAD_COMMENT_ACHIEVEMENTS = listOf(
            BAD_COMMENT_LEFT_1,
            BAD_COMMENT_LEFT_5,
            BAD_COMMENT_LEFT_10,
        )

        @JvmStatic
        val GOOD_COMMENT_ACHIEVEMENTS = listOf(
            GOOD_COMMENT_LEFT_1,
            GOOD_COMMENT_LEFT_5,
            GOOD_COMMENT_LEFT_10,
        )
    }
}
