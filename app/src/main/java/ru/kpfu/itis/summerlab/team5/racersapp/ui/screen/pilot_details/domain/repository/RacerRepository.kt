package ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.pilot_details.domain.repository

import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.pilot_details.domain.model.Racer

interface RacerRepository {
    suspend fun getAllRacers(): List<Racer>
    suspend fun getRacerById(id: Int): Racer?
    suspend fun getRacerByName(name: String): Racer?
}