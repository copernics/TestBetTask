package com.betsson.interviewtest.domain.utils

import com.betsson.interviewtest.domain.model.Bet

interface BetCommand {
    fun execute(bets: List<Bet>): List<Bet>
}

class SortBySellInCommand : BetCommand {
    override fun execute(bets: List<Bet>): List<Bet> {
        return bets.sortedBy { it.sellIn.value }
    }
}
class BetProcessor {
    private val commands = mutableListOf<BetCommand>()

    fun addCommand(command: BetCommand) {
        commands.add(command)
    }

    fun process(bets: List<Bet>): List<Bet> {
        var result = bets
        for (command in commands) {
            result = command.execute(result)
        }
        return result
    }
}