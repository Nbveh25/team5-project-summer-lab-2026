package ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.pilot_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.pilot_details.data.datasource.LocalRacerDataSource
import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.pilot_details.data.repository.RacerRepositoryImpl
import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.pilot_details.domain.usecase.GetAllRacersUseCase
import ru.kpfu.itis.summerlab.team5.racersapp.ui.screen.pilot_details.domain.usecase.GetRacerByIdUseCase

class PilotDetailsViewModelFactory(
    private val racerId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PilotDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PilotDetailsViewModel(
                getAllRacersUseCase = GetAllRacersUseCase(
                    RacerRepositoryImpl(LocalRacerDataSource())
                ),
                getRacerByIdUseCase = GetRacerByIdUseCase(
                    RacerRepositoryImpl(LocalRacerDataSource())
                ),
                initialRacerId = racerId
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}