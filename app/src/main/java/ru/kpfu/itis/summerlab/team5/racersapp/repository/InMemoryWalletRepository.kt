package ru.kpfu.itis.summerlab.team5.racersapp.repository

import ru.kpfu.itis.summerlab.team5.racersapp.domain.model.Wallet
import ru.kpfu.itis.summerlab.team5.racersapp.domain.repository.WalletRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InMemoryWalletRepository : WalletRepository {
    private val wallet = MutableStateFlow(Wallet(balanceRub = 10_000))

    override fun observeWallet(): StateFlow<Wallet> = wallet

    override fun spend(amountRub: Int) {
        wallet.value = wallet.value.copy(balanceRub = wallet.value.balanceRub - amountRub)
    }
}
