package com.kakapo.database.di

import com.kakapo.database.datasource.base.DatabaseTransactionDatasource
import com.kakapo.database.datasource.impl.DatabaseTransactionDatasourcceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindsTransactionDatasource(datasource: DatabaseTransactionDatasourcceImpl): DatabaseTransactionDatasource

}