package ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.pilot_details.data.datasource

import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.pilot_details.domain.model.Racer
import ru.kpfu.itis.summerlab.team5.racersapp.R


class LocalRacerDataSource {

    fun getAllRacers(): List<Racer> {
        return listOf(
            Racer(
                id = 1,
                name = "Гонщик 1",
                fullName = "Иванова Юлия Сергеевна",
                imageResId = R.drawable.first,
                bio = "Some text...",
                age = 19,
                country = "Россия",
                wins = 3,
                quote = "Quote 1 ..."
            ),
            Racer(
                id = 2,
                name = "Гонщик 2",
                fullName = "Краснова Анна Петровна",
                imageResId = R.drawable.second,
                bio = "Some text...",
                age = 18,
                country = "Россия",
                wins = 10,
                quote = "Quote 2 ..."
            ),
            Racer(
                id = 3,
                name = "Гонщик 3",
                fullName = "Маркова Ксения Яновна",
                imageResId = R.drawable.third,
                bio = "Some text...",
                age = 19,
                country = "Россия",
                wins = 2,
                quote = "Quote 3 ..."
            ),
            Racer(
                id = 4,
                name = "Гонщик 4",
                fullName = "Иванов Иван Иванович",
                imageResId = R.drawable.fourth,
                bio = "Some text...",
                age = 19,
                country = "Россия",
                wins = 7,
                quote = "Quote 4 ..."
            )
        )
    }

    fun getRacerById(id: Int): Racer? {
        return getAllRacers().find { it.id == id }
    }

    fun getRacerByName(name: String): Racer? {
        return getAllRacers().find { it.name.equals(name, ignoreCase = true) }
    }
}