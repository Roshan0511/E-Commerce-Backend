package example.com.plugins

import example.com.database.DatabaseFactory.dbQuery
import example.com.database.tables.TempVendor
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.exposed.sql.deleteReturning
import java.time.LocalDateTime

@OptIn(DelicateCoroutinesApi::class)
fun configureScheduledDeletion() {
    GlobalScope.launch(Dispatchers.Default) {
        while (true) {
//            delay(3600000) // Wait for 1 hour (3600000 ms)
            delay(120000) // Wait for 1 hour (3600000 ms)
            deleteOldTempVendors()
        }
    }
}

private suspend fun deleteOldTempVendors() {
    dbQuery {
        val twentyFourHoursAgo = LocalDateTime.now().minusMinutes(2)
        TempVendor.deleteReturning {
            TempVendor.createdAt.lessEq(twentyFourHoursAgo)
        }
    }
}