package com.javapda.cinemaroommanager

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CinemaRoomManagerTest {
    var cinemaRoomManager = CinemaRoomManager()

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun testStage1Arrangement() {
        val expectedArrangement =
            """Cinema:
  1 2 3 4 5 6 7 8
1 S S S S S S S S
2 S S S S S S S S
3 S S S S S S S S
4 S S S S S S S S
5 S S S S S S S S
6 S S S S S S S S
7 S S S S S S S S
"""
        assertEquals(expectedArrangement, cinemaRoomManager.arrangement())
    }

    @Test
    fun firstTest() {
        assertFalse(false)
        assertTrue(true)
        assertEquals(3, 3)
        assertThrows(IllegalArgumentException::class.java) {
            throw IllegalArgumentException("for testing")
        }
    }
}