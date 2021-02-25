package com.github.iryabov.achievements

import org.springframework.context.i18n.LocaleContextHolder
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle


data class Refbook(val id: Int, val name: String)
const val START_YEAR = 2018

fun getMonths(): List<Refbook> {
    val result = ArrayList<Refbook>()
    for (i in 1..12) {
        result.add(Refbook(i, getMonthName(i)))
    }
    return result
}

fun getYears(): List<Refbook> {
    val start = START_YEAR
    val current = LocalDate.now().year
    val result = ArrayList<Refbook>()
    for (i in start..current) {
        result.add(Refbook(i, i.toString()))
    }
    return result.sortedByDescending { it.id }
}

fun getMonthName(month: Int) = Month.of(month).getDisplayName(TextStyle.FULL_STANDALONE, LocaleContextHolder.getLocale()).capitalize()

fun getPeriod(year: Int, month: Int) = "${getMonthName(month)} $year"

fun getMonth(month: Int) = Refbook(month, getMonthName(month))

fun getYear(year: Int) = Refbook(year, year.toString())

fun getCurrentMonth() = getMonth(LocalDate.now().monthValue)

fun getCurrentYear() = getYear(LocalDate.now().year)

fun getCurrentPeriod() = getPeriod(LocalDate.now().year, LocalDate.now().monthValue)