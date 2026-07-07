package ru.kpfu.itis.summerlab.team5.racersapp.feauters.pilot_details.domain.model

data class Racer(
    val id: Int,
    val name: String,
    val fullName: String,
    val imageResId: Int,
    val bio: String,
    val age: Int,
    val country: String,
    val wins: Int,
    val quote: String
)