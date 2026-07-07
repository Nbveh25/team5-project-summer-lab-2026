package ru.kpfu.itis.summerlab.team5.racersapp.domain.model

data class RaceDetailsModel(
    val race: RaceModel,
    val teams: List<TeamModel>,
    val pilots: List<PilotModel>,
    val results: List<RaceResultModel>,
)
