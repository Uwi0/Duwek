package com.kakapo.common.utils

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long.toCurrentDate(): String {
    val instant = Instant.ofEpochMilli(this)
    val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate()
    val today = LocalDate.now()
    val tomorrow = today.plusDays(1)
    val yesterday = today.minusDays(1)
    val dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return when (date) {
        today -> "Today"
        tomorrow -> "Tomorrow"
        yesterday -> "Yesterday"
        else -> date.format(dtf)
    }
}