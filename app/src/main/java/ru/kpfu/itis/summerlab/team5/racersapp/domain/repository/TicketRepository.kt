package ru.kpfu.itis.summerlab.team5.racersapp.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.TicketCategoryModel
import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.TicketOfferModel

interface TicketRepository {
    fun observeTicketOffers(): Flow<List<TicketOfferModel>>

    fun purchaseTickets(selection: Map<TicketCategoryModel, Int>)
}
