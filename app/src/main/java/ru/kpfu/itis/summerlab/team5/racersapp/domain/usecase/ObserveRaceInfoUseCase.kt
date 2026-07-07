package ru.kpfu.itis.summerlab.team5.racersapp.domain.usecase

import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.RaceDetails
import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.Wallet
import ru.kpfu.itis.summerlab.team5.racersapp.domain.repository.RaceRepository
import ru.kpfu.itis.summerlab.team5.racersapp.domain.repository.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

data class RaceInfoData(
    val raceDetails: RaceDetails,
    val wallet: Wallet,
)

class ObserveRaceInfoUseCase(
    private val raceRepository: RaceRepository,
    private val walletRepository: WalletRepository,
) {
    operator fun invoke(): Flow<RaceInfoData> {
        return combine(
            raceRepository.observeRaceDetails(),
            walletRepository.observeWallet(),
        ) { raceDetails, wallet ->
            RaceInfoData(
                raceDetails = raceDetails,
                wallet = wallet,
            )
        }
    }
}
