package ru.kpfu.itis.summerlab.team5.racersapp.feauters.pilot_details.domain.usecase

import ru.kpfu.itis.summerlab.team5.racersapp.feauters.pilot_details.domain.model.Racer
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.pilot_details.domain.repository.RacerRepository

class GetAllRacersUseCase(
    private val repository: RacerRepository
) {
    suspend operator fun invoke(): List<Racer> = repository.getAllRacers()
}