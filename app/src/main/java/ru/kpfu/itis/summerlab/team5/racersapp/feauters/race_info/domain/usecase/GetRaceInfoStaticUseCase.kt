package ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.domain.usecase

import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.domain.model.RaceInfoStatic
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.domain.repository.RaceInfoStaticRepository
import ru.kpfu.itis.summerlab.team5.racersapp.utils.provider.ResourceProvider

class GetRaceInfoStaticUseCase(
    private val repository: RaceInfoStaticRepository,
    private val resourceProvider: ResourceProvider,
) {
    operator fun invoke(): RaceInfoStatic = repository.getRaceInfo(resourceProvider)
}

