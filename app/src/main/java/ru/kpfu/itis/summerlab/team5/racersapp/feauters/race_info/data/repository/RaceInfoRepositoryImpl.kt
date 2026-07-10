package ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.data.repository

import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.data.dataSource.RaceInfoDataSource
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.domain.model.RaceInfoStatic
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.domain.repository.RaceInfoStaticRepository
import ru.kpfu.itis.summerlab.team5.racersapp.utils.provider.ResourceProvider

class RaceInfoRepositoryImpl : RaceInfoStaticRepository {
    override fun getRaceInfo(resourceProvider: ResourceProvider): RaceInfoStatic =
        RaceInfoDataSource.getRaceInfo(resourceProvider)
}