package ru.easycode.zerotoheroandroidtdd.data

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Dao
interface ItemsDao {
    @Query("SELECT * FROM items ORDER BY id")
    fun list(): List<ItemCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(item: ItemCache)

    @Query("SELECT * FROM items WHERE id = :id")
    fun item(id: Long): ItemCache

    @Query("DELETE FROM items WHERE id = :id")
    fun delete(id: Long)
}


@Entity(tableName = "items")
data class ItemCache(
    @PrimaryKey val id: Long,
    val text: String
)