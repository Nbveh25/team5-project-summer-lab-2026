package ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.usecase


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.model.RaceModel
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.model.TicketOfferModel
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.model.WalletModel
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.repository.RaceRepository
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.repository.TicketRepository
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.repository.WalletRepository

data class TicketSelectionData(
    val race: RaceModel,
    val ticketOffers: List<TicketOfferModel>,
    val wallet: WalletModel,
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
