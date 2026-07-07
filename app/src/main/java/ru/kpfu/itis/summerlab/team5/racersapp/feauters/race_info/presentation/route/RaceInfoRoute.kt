package ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.presentation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.data.repository.RaceInfoRepositoryImpl
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.domain.usecase.GetRaceInfoStaticUseCase
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.presentation.screen.RaceInfoScreen
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.presentation.viewmodel.RaceInfoViewModel
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.race_info.presentation.viewmodel.RaceInfoViewModelFactory

@Composable
fun RaceInfoRoute(
    onMenuClick: () -> Unit = {},
    onBuyTicketClick: () -> Unit = {},
) {
    val repository = RaceInfoRepositoryImpl()
    val getRaceInfoUseCase = GetRaceInfoStaticUseCase(repository = repository)
    val viewModel: RaceInfoViewModel = viewModel(
        factory = RaceInfoViewModelFactory(getRaceInfoUseCase)
    )

    val uiState by viewModel.uiState.collectAsState()

    RaceInfoScreen(
        uiState = uiState,
        onMenuClick = {
            viewModel.onMenuClick()
            onMenuClick()
        },
        onBuyTicketClick = {
            viewModel.onBuyTicketClick()
            onBuyTicketClick()
        }
    )
}