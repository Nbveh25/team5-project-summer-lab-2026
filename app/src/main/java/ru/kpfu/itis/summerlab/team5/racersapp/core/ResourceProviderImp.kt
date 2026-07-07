package ru.kpfu.itis.summerlab.team5.racersapp.core

import android.content.Context

class ResourceProviderImp(private val context: Context) : ResourceProvider {
    override fun getString(resId: Int, vararg args: Any): String {
        return context.getString(resId, *args)
    }
}