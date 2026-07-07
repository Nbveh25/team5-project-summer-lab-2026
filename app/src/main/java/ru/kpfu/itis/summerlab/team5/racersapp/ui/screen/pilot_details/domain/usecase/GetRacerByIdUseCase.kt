package ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.pilot_details.domain.usecase

import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.pilot_details.domain.model.Racer
import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.pilot_details.domain.repository.RacerRepository

class GetRacerByIdUseCase(
    private val repository: RacerRepository
) {
    suspend operator fun invoke(id: Int): Racer? = repository.getRacerById(id)
}