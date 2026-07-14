package ru.easycode.zerotoheroandroidtdd.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordsDao {

    @Query("SELECT * FROM records ORDER BY id DESC")
    fun list(): Flow<List<RecordEntity>>

    @Insert
    suspend fun insert(entity: RecordEntity)

}