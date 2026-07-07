package ru.kpfu.itis.summerlab.team5.racersapp.domain.usecase

import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.TicketCategory
import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.TicketOffer
import ru.kpfu.itis.summerlab.team5.racersapp.domain.repository.TicketRepository
import ru.kpfu.itis.summerlab.team5.racersapp.domain.repository.WalletRepository

class PurchaseTicketsUseCase(
    private val ticketRepository: TicketRepository,
    private val walletRepository: WalletRepository,
) {
    operator fun invoke(
        offers: List<TicketOffer>,
        selection: Map<TicketCategory, Int>,
        currentBalanceRub: Int,
    ): Boolean {
        val total = offers.sumOf { offer ->
            offer.priceRub * selection.getValueOrZero(offer.category)
        }

        val hasEnoughMoney = total in 1..currentBalanceRub
        val hasEnoughTickets = offers.all { offer ->
            selection.getValueOrZero(offer.category) <= offer.ticketsLeft
        }

        if (!hasEnoughMoney || !hasEnoughTickets) {
            return false
        }

        walletRepository.spend(total)
        ticketRepository.purchaseTickets(selection)
        return true
    }

    private fun Map<TicketCategory, Int>.getValueOrZero(category: TicketCategory): Int {
        return this[category] ?: 0
    }
}
