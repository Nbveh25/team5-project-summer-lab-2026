package ru.kpfu.itis.summerlab.team5.racersapp.utils.core
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes resId: Int, vararg args: Any): String
}