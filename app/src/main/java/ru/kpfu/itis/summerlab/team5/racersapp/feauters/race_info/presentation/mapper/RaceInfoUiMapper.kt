package ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.presentation.mapper

import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.domain.model.RaceInfoStatic
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.presentation.model.RaceInfoUiModel
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.presentation.model.RaceResultRowUiModel


fun RaceInfoStatic.toUiModel(): RaceInfoUiModel =
    RaceInfoUiModel(
        title = title,
        balanceRub = balanceRub,
        imageResId = imageResId,
        resultRows = resultRows.map { row ->
            RaceResultRowUiModel(
                position = row.position,
                pilotName = row.pilotName,
                timeDelta = row.timeDelta,
                points = row.points,
            )
        },
    )