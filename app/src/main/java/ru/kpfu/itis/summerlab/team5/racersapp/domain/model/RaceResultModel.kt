package ru.kpfu.itis.summerlab.team5.racersapp.domain.model

data class RaceResultModel(
    val pilotId: String,
    val position: Int,
    val timeDelta: String,
    val points: Int,
)