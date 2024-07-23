package com.javapda.cinemaroommanager

/**
 * Cinema room manager
 *
 * @property rows
 * @property seats
 * @constructor Create empty Cinema room manager
 */
class CinemaRoomManager(val rows: Int = 7, val seats: Int = 8) {
    internal fun arrangement() =
        buildString {
            appendLine("Cinema:")
            append("  ")
            appendLine((1..seats).joinToString(" "))
            (1..rows).forEach { rowNumber ->
                appendLine("$rowNumber${" S".repeat(seats)}")
            }
        }

    fun stage1() {
        println(arrangement())
    }
}


fun main() {
    CinemaRoomManager().stage1()
}