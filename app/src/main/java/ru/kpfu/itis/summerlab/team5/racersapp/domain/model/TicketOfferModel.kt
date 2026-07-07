package ru.kpfu.itis.summerlab.team5.racersapp.domain.model

data class TicketOfferModel(
    val category: TicketCategoryModel,
    val title: String,
    val priceRub: Int,
    val ticketsLeft: Int,
)