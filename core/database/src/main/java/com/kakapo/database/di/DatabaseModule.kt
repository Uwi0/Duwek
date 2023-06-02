package com.kakapo.database.di

import android.content.Context
import androidx.room.Room
import com.kakapo.database.DATABASE_NAME
import com.kakapo.database.DuwekLocalDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDuwekLocalDB(@ApplicationContext context: Context): DuwekLocalDB {
        return Room.databaseBuilder(context, DuwekLocalDB::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}