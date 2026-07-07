package ru.kpfu.itis.summerlab.team5.racersapp.domain.usecase

import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.TicketOffer
import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.Wallet
import ru.kpfu.itis.summerlab.team5.racersapp.domain.repository.RaceRepository
import ru.kpfu.itis.summerlab.team5.racersapp.domain.repository.TicketRepository
import ru.kpfu.itis.summerlab.team5.racersapp.domain.repository.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.RaceModel

data class TicketSelectionData(
    val race: RaceModel,
    val ticketOffers: List<TicketOffer>,
    val wallet: Wallet,
)

class ObserveTicketSelectionUseCase(
    private val raceRepository: RaceRepository,
    private val ticketRepository: TicketRepository,
    private val walletRepository: WalletRepository,
) {
    operator fun invoke(): Flow<TicketSelectionData> {
        return combine(
            raceRepository.observeRaceDetails(),
            ticketRepository.observeTicketOffers(),
            walletRepository.observeWallet(),
        ) { raceDetails, ticketOffers, wallet ->
            TicketSelectionData(
                race = raceDetails.race,
                ticketOffers = ticketOffers,
                wallet = wallet,
            )
        }
    }
}
