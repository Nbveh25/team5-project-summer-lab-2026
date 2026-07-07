package ru.kpfu.itis.summerlab.team5.racersapp.feauters.pilot_details.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.kpfu.itis.summerlab.team5.racersapp.R
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.pilot_details.domain.model.Racer

data class PilotDetailsUiState(
    val currentRacer: RacerUiModel? = null,
    val allRacers: List<RacerUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val isMenuVisible: Boolean = false,
    val error: String? = null
)

data class RacerUiModel(
    val id: Int,
    val name: String,
    val fullName: String,
    val imageResId: Int,
    val bio: String,
    val age: Int,
    val country: String,
    val wins: Int,
    val quote: String
) {
    val formattedAge: String @Composable
    get() = stringResource(id = R.string.racer_agev2, age)
    val formattedWins: String @Composable
    get() = stringResource(id = R.string.racer_winsv2, wins)
    val formattedQuote: String @Composable
    get() = stringResource(id = R.string.racer_quotev2, quote)
}


fun Racer.toUiModel(): RacerUiModel {
    return RacerUiModel(
        id = id,
        name = name,
        fullName = fullName,
        imageResId = imageResId,
        bio = bio,
        age = age,
        country = country,
        wins = wins,
        quote = quote
    )
}

fun List<Racer>.toUiModelList(): List<RacerUiModel> {
    return this.map { it.toUiModel() }
}