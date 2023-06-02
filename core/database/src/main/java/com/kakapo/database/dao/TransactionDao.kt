package com.kakapo.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kakapo.database.constants.DatabaseConstants
import com.kakapo.database.model.DatabaseTransaction

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataTransaction(transaction: DatabaseTransaction)

    @Query("SELECT * FROM `${DatabaseConstants.TABLE_TRANSACTION}`")
    suspend fun getAllListTransaction(): List<DatabaseTransaction>
}