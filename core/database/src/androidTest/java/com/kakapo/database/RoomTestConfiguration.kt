package com.kakapo.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before
import java.io.IOException
import kotlin.jvm.Throws

abstract class RoomTestConfiguration {

    open lateinit var database: DuwekLocalDB


    @Before
    fun setup(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, DuwekLocalDB::class.java).build()
        initTest()
    }

    @After
    @Throws(IOException::class)
    fun tearDown(){
        database.close()
    }

    abstract fun initTest()
}