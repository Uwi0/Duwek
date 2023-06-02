package com.kakapo.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kakapo.database.constants.DatabaseConstants
import com.kakapo.model.transaction.TransactionType


@Entity(tableName = DatabaseConstants.TABLE_TRANSACTION)
data class DatabaseTransaction(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DatabaseConstants.TRANSACTION_ID)
    val id: Long = 0,
    @ColumnInfo(name = DatabaseConstants.TRANSACTION_AMOUNT)
    val amount: Double,
    @ColumnInfo(name = DatabaseConstants.TRANSACTION_CATEGORY)
    val category: String,
    @ColumnInfo(name = DatabaseConstants.TRANSACTION_DATE)
    val date: String,
    @ColumnInfo(name = DatabaseConstants.TRANSACTION_DESCRIPTION)
    val description: String,
    @ColumnInfo(name = DatabaseConstants.TRANSACTION_TYPE)
    val transactionType: TransactionType
)
