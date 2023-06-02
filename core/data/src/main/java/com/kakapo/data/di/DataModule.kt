package com.kakapo.data.di

import com.kakapo.data.repository.base.TransactionRepository
import com.kakapo.data.repository.impl.TransactionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindTransactionRepository(repository: TransactionRepositoryImpl): TransactionRepository

}