package ru.kpfu.itis.summerlab.team5.racersapp.domain.model

import androidx.annotation.DrawableRes

data class RaceModel(
    val id: String,
    val title: String,
    val location: String,
    @DrawableRes val imageResId: Int,
)
