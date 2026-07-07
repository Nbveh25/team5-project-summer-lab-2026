package ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.race_info.data.dataSource

import ru.kpfu.itis.summerlab.team5.racersapp.R
import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.race_info.domain.model.RaceInfoStatic
import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.race_info.domain.model.RaceResultRowStatic

object RaceInfoDataSource {
    val raceInfo: RaceInfoStatic =
        RaceInfoStatic(
            title = "Бик Тиз",
            balanceRub = 0,
            imageResId = R.drawable.team,
            resultRows = listOf(
                RaceResultRowStatic(
                    position = 1,
                    pilotName = "Гонщик 1",
                    timeDelta = "+13.722S",
                    points = 18
                ),
                RaceResultRowStatic(
                    position = 2,
                    pilotName = "Гонщик 2",
                    timeDelta = "+15.27S",
                    points = 15
                ),
                RaceResultRowStatic(
                    position = 3,
                    pilotName = "Гонщик 3",
                    timeDelta = "+15.754S",
                    points = 12
                ),
                RaceResultRowStatic(
                    position = 4,
                    pilotName = "Гонщик 4",
                    timeDelta = "+23.479S",
                    points = 10
                ),
            ),
        )
}