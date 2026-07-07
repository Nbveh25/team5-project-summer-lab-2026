package ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.usecase

import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.model.TicketCategoryModel
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.model.TicketOfferModel
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.repository.TicketRepository
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.repository.WalletRepository

class PurchaseTicketsUseCase(
    private val ticketRepository: TicketRepository,
    private val walletRepository: WalletRepository,
) {
    operator fun invoke(
        offers: List<TicketOfferModel>,
        selection: Map<TicketCategoryModel, Int>,
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

    private fun Map<TicketCategoryModel, Int>.getValueOrZero(category: TicketCategoryModel): Int {
        return this[category] ?: 0
    }
}
