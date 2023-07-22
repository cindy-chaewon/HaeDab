package haedab.haedab.haedab.database

import androidx.room.Database
import androidx.room.RoomDatabase
import haedab.haedab.haedab.data.local.MessageDao


@Database(entities = [RoomEntity::class], version = 1, exportSchema = false)
abstract class MessageDatabase: RoomDatabase() {

    abstract fun messageDao(): MessageDao
}