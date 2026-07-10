package ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.domain

interface TicketRepository {

    fun getTickets(): Tickets

    fun saveTickets(tickets: Tickets)
}