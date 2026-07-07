package ru.kpfu.itis.summerlab.team5.racersapp.repository

import ru.kpfu.itis.summerlab.team5.racersapp.R
import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.Pilot
import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.Race
import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.RaceDetails
import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.RaceResult
import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.Team
import ru.kpfu.itis.summerlab.team5.racersapp.domain.repository.RaceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InMemoryRaceRepository : RaceRepository {
    private val raceDetails = MutableStateFlow(
        RaceDetails(
            race = Race(
                id = "big-tiz",
                title = "NFS",
                location = "Australia",
                imageResId = R.drawable.team,
            ),
            teams = listOf(
                Team(id = "meteor", name = "Meteor Racing"),
                Team(id = "thunder", name = "Thunder GP"),
            ),
            pilots = listOf(
                Pilot(id = "1", name = "Гонщик1", teamId = "meteor"),
                Pilot(id = "2", name = "Гонщик2", teamId = "meteor"),
                Pilot(id = "3", name = "Гонщик3", teamId = "thunder"),
                Pilot(id = "4", name = "Гонщик4", teamId = "thunder"),
            ),
            results = listOf(
                RaceResult(pilotId = "1", position = 1, timeDelta = "+13.722S", points = 18),
                RaceResult(pilotId = "2", position = 2, timeDelta = "+15.27S", points = 15),
                RaceResult(pilotId = "3", position = 3, timeDelta = "+15.754S", points = 12),
                RaceResult(pilotId = "4", position = 4, timeDelta = "+23.479S", points = 10),
            ),
        ),
    )

    override fun observeRaceDetails(): StateFlow<RaceDetails> = raceDetails
}
