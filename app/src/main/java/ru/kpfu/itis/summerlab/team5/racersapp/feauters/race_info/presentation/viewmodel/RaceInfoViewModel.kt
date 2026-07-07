package ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.presentation.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.domain.usecase.GetRaceInfoStaticUseCase
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.presentation.mapper.toUiModel
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.presentation.screen.RaceInfoUiState

class RaceInfoViewModel(
    private val getRaceInfoUseCase: GetRaceInfoStaticUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RaceInfoUiState>(RaceInfoUiState.Loading)
    val uiState: StateFlow<RaceInfoUiState> = _uiState.asStateFlow()

    init {
        loadRaceInfo()
    }

    private fun loadRaceInfo() {
        viewModelScope.launch {
            try {
                val domainModel = getRaceInfoUseCase()
                _uiState.value = RaceInfoUiState.Success(domainModel.toUiModel())
            } catch (e: Exception) {
                _uiState.value = RaceInfoUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun onMenuClick() {
        // Логика при клике на меню
    }

    fun onBuyTicketClick() {
        // Логика при клике на покупку билета
    }
}