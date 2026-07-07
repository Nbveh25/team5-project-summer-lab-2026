package ru.kpfu.itis.summerlab.team5.racersapp.repository

import kotlinx.coroutines.flow.Flow
import ru.kpfu.itis.summerlab.team5.racersapp.domain.repository.WalletRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.WalletModel

class InMemoryWalletRepository : WalletRepository {
    private val wallet = MutableStateFlow(WalletModel(balanceRub = 10_000))

    override fun observeWallet(): Flow<WalletModel> = wallet

    override fun spend(amountRub: Int) {
        wallet.value = wallet.value.copy(balanceRub = wallet.value.balanceRub - amountRub)
    }
}
