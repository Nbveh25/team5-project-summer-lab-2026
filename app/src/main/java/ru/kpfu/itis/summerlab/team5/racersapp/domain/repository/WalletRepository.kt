package ru.kpfu.itis.summerlab.team5.racersapp.domain.repository

import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.Wallet
import kotlinx.coroutines.flow.StateFlow

interface WalletRepository {
    fun observeWallet(): StateFlow<Wallet>

    fun spend(amountRub: Int)
}
