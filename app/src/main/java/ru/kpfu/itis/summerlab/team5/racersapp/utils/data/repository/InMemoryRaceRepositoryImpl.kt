package ru.kpfu.itis.summerlab.team5.racersapp.utils.data.repository

import  kotlinx.coroutines.flow.Flow

import ru.kpfu.itis.summerlab.team5.racersapp.R
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.repository.RaceRepository

import kotlinx.coroutines.flow.MutableStateFlow
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.model.PilotModel
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.model.RaceDetailsModel
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.model.RaceModel
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.model.RaceResultModel
import ru.kpfu.itis.summerlab.team5.racersapp.utils.domain.model.TeamModel


class InMemoryRaceRepositoryImpl : RaceRepository {
    private val raceDetails = MutableStateFlow(
        RaceDetailsModel(
            race = RaceModel(
                id = "big-tiz",
                title = "NFS",
                location = "Australia",
                imageResId = R.drawable.team,
            ),
            teams = listOf(
                TeamModel(id = "meteor", name = "Meteor Racing"),
                TeamModel(id = "thunder", name = "Thunder GP"),
            ),
            pilots = listOf(
                PilotModel(id = "1", name = "Гонщик1", teamId = "meteor"),
                PilotModel(id = "2", name = "Гонщик2", teamId = "meteor"),
                PilotModel(id = "3", name = "Гонщик3", teamId = "thunder"),
                PilotModel(id = "4", name = "Гонщик4", teamId = "thunder"),
            ),
            results = listOf(
                RaceResultModel(pilotId = "1", position = 1, timeDelta = "+13.722S", points = 18),
                RaceResultModel(pilotId = "2", position = 2, timeDelta = "+15.27S", points = 15),
                RaceResultModel(pilotId = "3", position = 3, timeDelta = "+15.754S", points = 12),
                RaceResultModel(pilotId = "4", position = 4, timeDelta = "+23.479S", points = 10),
            ),
        ),
    )

    override fun observeRaceDetails(): Flow<RaceDetailsModel> = raceDetails
}
