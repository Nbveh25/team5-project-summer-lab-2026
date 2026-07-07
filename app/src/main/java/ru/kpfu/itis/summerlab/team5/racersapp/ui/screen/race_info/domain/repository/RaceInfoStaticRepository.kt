package ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.race_info.domain.repository

import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.race_info.domain.model.RaceInfoStatic

interface RaceInfoStaticRepository {
    fun getRaceInfo(): RaceInfoStatic
}