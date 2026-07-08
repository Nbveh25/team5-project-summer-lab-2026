package ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.domain.usecase

import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.domain.model.RaceInfoStatic
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.domain.repository.RaceInfoStaticRepository

class GetRaceInfoStaticUseCase(
    private val repository: RaceInfoStaticRepository,
) {
    operator fun invoke(): RaceInfoStatic = repository.getRaceInfo()
}

