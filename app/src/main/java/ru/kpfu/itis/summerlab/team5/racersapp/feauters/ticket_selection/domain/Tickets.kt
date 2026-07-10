package ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.domain

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Tickets(
    val FanZone: Int, val VipZone: Int, val PremiumZone: Int
)