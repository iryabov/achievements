package com.github.iryabov.achievements

import org.springframework.context.i18n.LocaleContextHolder
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle


data class Refbook(val id: Int, val name: String)

const val START_YEAR = 2018
const val START_MONTH = 5

fun getMonths(): List<Refbook> {
    val result = ArrayList<Refbook>()
    for (i in 1..12) {
        result.add(getMonth(i))
    }
    return result
}

fun getYears(): List<Refbook> {
    val start = START_YEAR
    val current = LocalDate.now().year
    val result = ArrayList<Refbook>()
    for (i in start..current) {
        result.add(getYear(i))
    }
    return result.sortedByDescending { it.id }
}

fun getPeriods(): List<Refbook> {
    val startYear = START_YEAR
    val startMonth = START_MONTH
    val current = LocalDate.now().minusMonths(1)
    val result = ArrayList<Refbook>()
    for (y in startYear..current.year) {
        val startOfMonth = if (y == START_YEAR) startMonth else 1
        val endOfMonth = if (y == current.year) current.monthValue else 12
        for (m in startOfMonth..endOfMonth) {
            result.add(getPeriod(y * 100 + m))
        }
    }
    return result.sortedByDescending { it.id }
}

fun getMonthName(month: Int) =
    Month.of(month).getDisplayName(TextStyle.FULL_STANDALONE, LocaleContextHolder.getLocale()).capitalize()

fun getPeriodName(year: Int, month: Int) = "${getMonthName(month)} $year"

fun getMonth(month: Int) = Refbook(month, getMonthName(month))

fun getYear(year: Int) = Refbook(year, year.toString())

fun getPeriod(yearmonth: Int) = Refbook(yearmonth, getPeriodName(yearmonth / 100, yearmonth % 100))

fun getCurrentMonth() = getMonth(LocalDate.now().monthValue)

fun getCurrentYear() = getYear(LocalDate.now().year)

fun getCurrentPeriod() = getPeriodName(LocalDate.now().year, LocalDate.now().monthValue)