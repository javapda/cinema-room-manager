package com.javapda.cinemaroommanager

data class Seat(val row: Int, val seatNumber: Int, var cost: Int = 0) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Seat

        if (row != other.row) return false
        if (seatNumber != other.seatNumber) return false

        return true
    }

    override fun hashCode(): Int {
        var result = row
        result = 31 * result + seatNumber
        return result
    }
}

class CinemaRoom(private val rows: Int = 7, private val seatsPerRow: Int = 8) {
    fun seatCost(seat: Seat): Int =
        if (rows * seatsPerRow <= 60 || seat.row <= rows / 2) 10 else 8

    fun totalIncome(): Int {
        var sum = 0
        (1..rows).forEach { rowNumber ->
            (1..seatsPerRow).forEach { seatNumber ->
                sum += seatCost(Seat(rowNumber, seatNumber))
            }
        }
        return sum
    }

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

    private fun isValidSeat(seat: Seat): Boolean =
        seat.row in 1..rows && seat.seatNumber in 1..seatsPerRow

    fun isInvalidSeat(seat: Seat): Boolean = !isValidSeat(seat)
    fun numberOfSeats(): Int = rows * seatsPerRow

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


    fun stage5() {
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
                3. Statistics 
                0. Exit
                > 
            """.trimIndent()
            )
            return readln().trim()
        }

        fun showTheSeats() {
            println(cinemaRoom.renderRoom(reservedSeats))
        }

        fun statistics() {
            val currentIncome = reservedSeats.sumOf { seat -> seat.cost }
            val totalIncome = cinemaRoom.totalIncome()
            val numberOfPurchasedTickets = reservedSeats.size
            val percentage = reservedSeats.size.toDouble() * 100.0 / cinemaRoom.numberOfSeats().toDouble()
//            val percentage = currentIncome.toDouble()*100.0/totalIncome.toDouble()
            val formatPercentage = "%.2f".format(percentage)
            println(
                """
                Number of purchased tickets: $numberOfPurchasedTickets
                Percentage: $formatPercentage%
                Current income: ${'$'}$currentIncome
                Total income: ${'$'}$totalIncome
                
            """.trimIndent()
            )
        }

        fun buyATicket() {
            print("Enter a row number:\n> ")
            val rowNumber = readln().toInt()
            print("Enter a seat number in that row:\n> ")
            val seatNumber = readln().toInt()
            val seat = Seat(rowNumber, seatNumber)
            if (cinemaRoom.isInvalidSeat(seat)) {
                println("\nWrong input!\n")
                buyATicket()
            } else if (seat in reservedSeats) {
                println("\nThat ticket has already been purchased!\n")
                buyATicket()
            } else {
                seat.cost = cinemaRoom.seatCost(seat)
                println(
                    """
            
            Ticket price: ${'$'}${seat.cost}
            
        """.trimIndent()
                )

                reservedSeats.add(seat)
            }
        }

        var response = ""
        while (response != "0") {
            response = menu()
            println()
            when (response) {
                "1" -> showTheSeats()
                "2" -> buyATicket()
                "3" -> statistics()
                "0" -> {}
                else -> {
                    println("Warning: do not know how to handle input '$response'")
                }
            }
        }

    }

}

fun main() {
    CinemaRoomManager().stage5()
}