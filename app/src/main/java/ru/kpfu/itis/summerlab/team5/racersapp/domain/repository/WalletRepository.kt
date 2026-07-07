package ru.kpfu.itis.summerlab.team5.racersapp.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.WalletModel

interface WalletRepository {
    fun observeWallet(): Flow<WalletModel>

    fun spend(amountRub: Int)
}
