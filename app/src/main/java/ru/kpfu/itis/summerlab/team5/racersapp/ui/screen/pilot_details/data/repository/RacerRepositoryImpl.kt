package ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.pilot_details.data.repository

import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.pilot_details.data.datasource.LocalRacerDataSource
import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.pilot_details.domain.model.Racer
import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.pilot_details.domain.repository.RacerRepository

class RacerRepositoryImpl(
    private val localDataSource: LocalRacerDataSource
) : RacerRepository {

    override suspend fun getAllRacers(): List<Racer> {
        return localDataSource.getAllRacers()
    }

    override suspend fun getRacerById(id: Int): Racer? {
        return localDataSource.getRacerById(id)
    }

    override suspend fun getRacerByName(name: String): Racer? {
        return localDataSource.getRacerByName(name)
    }
}