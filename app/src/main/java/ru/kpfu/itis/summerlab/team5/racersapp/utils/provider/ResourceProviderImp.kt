package ru.kpfu.itis.summerlab.team5.racersapp.utils.provider

import android.content.Context

class ResourceProviderImpl(private val context: Context) : ResourceProvider {
    override fun getString(resId: Int, vararg args: Any): String {
        return context.getString(resId, *args)
    }
}