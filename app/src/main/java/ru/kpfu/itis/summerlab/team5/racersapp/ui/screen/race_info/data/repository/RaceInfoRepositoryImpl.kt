package ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.race_info.data.repository

import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.race_info.data.dataSource.RaceInfoDataSource
import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.race_info.domain.model.RaceInfoStatic
import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.race_info.domain.repository.RaceInfoStaticRepository

class RaceInfoRepositoryImpl : RaceInfoStaticRepository {
    override fun getRaceInfo(): RaceInfoStatic = RaceInfoDataSource.raceInfo
}