package com.kakapo.data.result

suspend fun <T> proceed(coroutine: suspend () -> T): T {
    return try {
        coroutine.invoke()
    }catch (e: Exception){
        throw e
    }
}