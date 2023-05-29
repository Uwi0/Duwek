package com.kakapo.model.home

import io.github.serpro69.kfaker.Faker

data class Wallet(
    val id: Int = 0,
    val name: String = "",
    val img: String = "",
    val balance: String = ""
)

fun fakeDataWallet(): List<Wallet> {
    val faker = Faker()
    return List(10){
        Wallet(id = faker.idNumber.unique.invalid().toInt(), name = faker.name.firstName(), img = "", balance = "1000")
    }
}
