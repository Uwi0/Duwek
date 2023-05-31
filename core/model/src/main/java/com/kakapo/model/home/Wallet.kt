package com.kakapo.model.home

import io.github.serpro69.kfaker.Faker
import java.util.UUID

data class Wallet(
    val id: Int = 0,
    val name: String = "",
    val img: String = "",
    val balance: String = ""
)

fun fakeDataWallet(): List<Wallet> {
    val faker = Faker()
    return List(10){
        Wallet(id = Math.random().toInt(), name = faker.name.firstName(), img = "", balance = "1000")
    }
}
