package ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.data

import android.content.Context
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.domain.TicketRepository
import ru.kpfu.itis.summerlab.team5.racersapp.feauters.ticket_selection.domain.TicketRepositoryFactory
import java.io.File

class FileTicketRepositoryFactory(
    private val context: Context
) : TicketRepositoryFactory {

    override fun createRepository(city: String): TicketRepository {
        val safeCityName = city.replace(Regex("[^A-Za-z0-9]"), "_")
        val file = File(context.filesDir, "tickets_$safeCityName.json")
        
        if (!file.exists()) {
            file.writeText("""{"FanZone":100,"VipZone":50,"PremiumZone":20}""")
        }
        
        return TicketRepositoryImpl(file)
    }
}
