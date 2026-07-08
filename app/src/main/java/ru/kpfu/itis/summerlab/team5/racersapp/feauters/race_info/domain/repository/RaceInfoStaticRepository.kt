package ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.domain.repository

import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.domain.model.RaceInfoStatic

interface RaceInfoStaticRepository {
    fun getRaceInfo(): RaceInfoStatic
}