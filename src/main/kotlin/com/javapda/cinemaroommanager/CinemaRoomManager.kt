package com.javapda.cinemaroommanager

data class Seat(val row: Int, val seatNumber: Int)
class CinemaRoom(val rows: Int = 7, val seatsPerRow: Int = 8) {
    fun seatCost(seat: Seat): Int =
        if (rows * seatsPerRow <= 60 || seat.row <= rows / 2) 10 else 8

    fun renderRoom(reservedSeats: Set<Seat>) =
        buildString {
            appendLine("Cinema:")
            append("  ")
            appendLine((1..seatsPerRow).joinToString(" "))
            (1..rows).forEach { rowNumber ->
                append(rowNumber).append(" ")
                (1..seatsPerRow).forEach { seatNumber ->
                    append(if (Seat(rowNumber, seatNumber) in reservedSeats) "B" else "S")
                    if (seatNumber < seatsPerRow) append(" ")
                }
                appendLine()
            }

        }

}

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

    internal fun showSeats() {

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

    fun stage4() {
        val reservedSeats = mutableSetOf<Seat>()
        print("Enter the number of rows:\n> ")
        val rows = readln().toInt()
        print("Enter the number of seats in each row:\n> ")
        val seatsPerRow = readln().toInt()
        val cinemaRoom = CinemaRoom(rows, seatsPerRow)
        fun menu(): String {
            print(
                """
                1. Show the seats
                2. Buy a ticket
                0. Exit
                > 
            """.trimIndent()
            )
            return readln().trim()
        }

        fun showTheSeats() {
            println(cinemaRoom.renderRoom(reservedSeats))
        }

        fun buyATicket() {
            print("Enter a row number:\n> ")
            val rowNumber = readln().toInt()
            print("Enter a seat number in that row:\n> ")
            val seatNumber = readln().toInt()
            val seat = Seat(rowNumber, seatNumber)
            println(
                """
            
            Ticket price: ${'$'}${cinemaRoom.seatCost(seat)}
            
        """.trimIndent()
            )
            reservedSeats.add(seat)
        }

        var response = ""
        while (response != "0") {
            response = menu()
            println()
            when (response) {
                "1" -> showTheSeats()
                "2" -> buyATicket()
                "0" -> {}
                else -> {
                    println("Warning: do not know how to handle input '$response'")
                }
            }
        }

    }

}

fun main() {
    CinemaRoomManager().stage4()
}