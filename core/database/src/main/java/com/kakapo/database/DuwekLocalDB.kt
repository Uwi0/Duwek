package com.kakapo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kakapo.database.dao.TransactionDao
import com.kakapo.database.model.DatabaseTransaction

const val DATABASE_NAME = "duwekLocalDB.db"
private const val DATABASE_VERSION = 1

@Database(
    entities = [
        DatabaseTransaction::class
    ],
    version = DATABASE_VERSION
)
abstract class DuwekLocalDB: RoomDatabase() {

    abstract fun transactionDao(): TransactionDao


}