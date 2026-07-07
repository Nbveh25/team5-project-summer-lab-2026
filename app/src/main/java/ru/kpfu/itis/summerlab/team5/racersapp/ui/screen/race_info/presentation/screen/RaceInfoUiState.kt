package ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.race_info.presentation.screen

import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.race_info.presentation.model.RaceInfoUiModel

sealed class RaceInfoUiState {
    object Loading : RaceInfoUiState()
    data class Success(val uiModel: RaceInfoUiModel) : RaceInfoUiState()
    data class Error(val message: String) : RaceInfoUiState()
}