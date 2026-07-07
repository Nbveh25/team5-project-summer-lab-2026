package ru.kpfu.itis.summerlab.team5.racersapp.utils.repository


import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.model.TicketCategoryModel
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.model.TicketOfferModel
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.repository.TicketRepository


class InMemoryTicketRepository : TicketRepository {
    private val ticketOffers = MutableStateFlow(
        listOf(
            TicketOfferModel(
                category = TicketCategoryModel.FAN_ZONE,
                title = "Fan Zone",
                priceRub = 100,
                ticketsLeft = 100,
            ),
            TicketOfferModel(
                category = TicketCategoryModel.VIP_ZONE,
                title = "VIP Zone",
                priceRub = 1400,
                ticketsLeft = 107,
            ),
            TicketOfferModel(
                category = TicketCategoryModel.PREMIUM_ZONE,
                title = "Premium Zone",
                priceRub = 2500,
                ticketsLeft = 107,
            ),
        ),
    )

    override fun observeTicketOffers(): Flow<List<TicketOfferModel>> = ticketOffers

    override fun purchaseTickets(selection: Map<TicketCategoryModel, Int>) {
        ticketOffers.value = ticketOffers.value.map { offer ->
            val purchased = selection[offer.category].orZero()
            offer.copy(ticketsLeft = offer.ticketsLeft - purchased)
        }
    }

    private fun Int?.orZero(): Int = this ?: 0
}
