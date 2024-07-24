package com.javapda.cinemaroommanager

/**
 * Cinema room manager
 *
 * @property rows
 * @property seats
 * @constructor Create empty Cinema room manager
 */
class CinemaRoomManager(val rows: Int = 7, val seats: Int = 8) {
    internal fun arrangement() = buildString {
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

    /**
     * Calculate revenue
     *
     * If the total number of seats in the screen room is not more than 60, then the price of each ticket
     * is 10 dollars.
     *
     * In a larger room, the tickets are 10 dollars for the front half of the rows and 8 dollars for the
     * back half. Please note that the number of rows can be odd, for example, 9 rows. In this case, the
     * first half is the first 4 rows, and the second half is the rest 5 rows.
     *
     * @param rows
     * @param seatsPerRow
     * @return
     */
    @SuppressWarnings("MagicNumber")
    fun calculateRevenue(rows: Int, seatsPerRow: Int): Int {
        val highDollarSeatCost = 10
        val lowDollarSeatCost = 8
        return if (rows * seatsPerRow <= 60) {
            // small room
            rows * seatsPerRow * highDollarSeatCost
        } else {
            // large room
            val highCostSeatRows = rows / 2
            seatsPerRow * (highCostSeatRows * highDollarSeatCost + (rows - highCostSeatRows) * lowDollarSeatCost)
        }

    }

    fun stage2() {
        print("Enter the number of rows:\n> ")
        val rows = readln().toInt()
        print("Enter the number of seats in each row:\n> ")
        val seatsPerRow = readln().toInt()
        println(
            """
            Total income:
            $${calculateRevenue(rows, seatsPerRow)}
        """.trimIndent()
        )

    }
}

fun main() {
    CinemaRoomManager().stage2()
}