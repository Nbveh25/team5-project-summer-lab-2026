package ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.presentation.model

import androidx.annotation.DrawableRes

data class RaceInfoUiModel(
    val title: String,
    val balanceRub: Int,
    @DrawableRes val imageResId: Int,
    val resultRows: List<RaceResultRowUiModel>,
)

data class RaceResultRowUiModel(
    val position: Int,
    val pilotName: String,
    val timeDelta: String,
    val points: Int,
)

