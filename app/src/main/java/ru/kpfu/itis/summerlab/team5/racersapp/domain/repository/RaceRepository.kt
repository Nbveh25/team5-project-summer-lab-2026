package ru.kpfu.itis.summerlab.team5.racersapp.domain.repository

import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.RaceDetails
import kotlinx.coroutines.flow.StateFlow

interface RaceRepository {
    fun observeRaceDetails(): StateFlow<RaceDetails>
}
