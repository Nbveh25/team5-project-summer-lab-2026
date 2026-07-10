package ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.domain

interface TicketRepositoryFactory {
    fun createRepository(city: String): TicketRepository
}
