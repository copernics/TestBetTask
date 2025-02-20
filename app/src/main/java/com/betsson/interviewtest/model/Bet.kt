package com.betsson.interviewtest.model
//Better using data class with 'val' instead of 'var' arguments
//Also I'm using 'value' classes for better readability
data class Bet(
    val type: BetType,
    val sellIn: SellInDays,
    val odds: Odds,
    val image: ImagePath
) {
    //override toString() to be like the previous one. Honestly I don't like it
    override fun toString(): String {
        return "Bet(type=${type.displayName}, sellIn=${sellIn.value}, odds=${odds.value})"
    }
}

//Decide move into enum class, because of better readability
enum class BetType(val displayName: String) {
    WINNING_TEAM("Winning team"),
    TOTAL_SCORE("Total score"),
    PLAYER_PERFORMANCE("Player performance"),
    FIRST_GOAL_SCORER("First goal scorer"),
    NUMBER_OF_FOULS("Number of fouls"),
    CORNER_KICKS("Corner kicks");
}

//We can Added small related logic to value classes at init.
@JvmInline
value class SellInDays(val value: Int)

@JvmInline
value class Odds(val value: Int) {
    init {
        require(value >= 0) { "Odds should be less than 0" }
    }
}

@JvmInline
value class ImagePath(val value: String) {
    init {
        require(value.isNotBlank()) { "Image path can't be a blank" }
    }
}
