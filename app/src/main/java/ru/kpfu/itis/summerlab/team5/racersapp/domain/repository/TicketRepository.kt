package ru.kpfu.itis.summerlab.team5.racersapp.domain.repository

import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.TicketCategory
import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.TicketOffer
import kotlinx.coroutines.flow.StateFlow

interface TicketRepository {
    fun observeTicketOffers(): StateFlow<List<TicketOffer>>

    fun purchaseTickets(selection: Map<TicketCategory, Int>)
}
