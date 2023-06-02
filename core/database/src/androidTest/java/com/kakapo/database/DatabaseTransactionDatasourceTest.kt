package com.kakapo.database

import com.kakapo.database.datasource.base.DatabaseTransactionDatasource
import com.kakapo.database.datasource.impl.DatabaseTransactionDatasourcceImpl
import com.kakapo.database.model.DatabaseTransaction
import com.kakapo.model.transaction.TransactionType
import io.github.serpro69.kfaker.Faker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class DatabaseTransactionDatasourceTest: RoomTestConfiguration() {

    private lateinit var transactionDatasource: DatabaseTransactionDatasource
    override fun initTest() {
        transactionDatasource = DatabaseTransactionDatasourcceImpl(database)
    }

    @Test
    fun addTransaction_must_increase_databaseSize() = runTest {
        val listTransaction = transactionDataGenerator()
        listTransaction.forEach {
            transactionDatasource.addTransaction(it)
        }
        val result = transactionDatasource.listTransaction()
        assertTrue(result.isNotEmpty())
    }

    private fun transactionDataGenerator(): List<DatabaseTransaction> {
        val faker = Faker()
        val randomNumber = (1..10).random()
        val isEven = randomNumber % 2 == 0
        val transactionType = if (isEven) TransactionType.INCOME else TransactionType.INCOME
        return List(10){
            DatabaseTransaction(
                category = faker.animal.name(),
                date = "",
                description = faker.lorem.words(),
                transactionType = transactionType,
                amount = 0.0
            )
        }
    }
}