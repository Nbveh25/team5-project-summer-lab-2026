package ru.kpfu.itis.summerlab.team5.racersapp.utils.provider
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes resId: Int, vararg args: Any): String
}