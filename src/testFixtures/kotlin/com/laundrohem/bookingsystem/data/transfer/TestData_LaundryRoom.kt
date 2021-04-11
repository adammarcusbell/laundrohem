package com.laundrohem.bookingsystem.data.transfer

import java.util.*

class TestData_LaundryRoom {

    companion object {
        val LAUNDRY_ROOM_ONE = LaundryRoom(UUID.randomUUID(), "Laundry Room One", listOf(TestData_Appliance.ELECROTROLUX_WASHER_LR1,
            TestData_Appliance.ELECTROLUX_DRYER_LR1))

        val LAUNDRY_ROOM_TWO = LaundryRoom(UUID.randomUUID(), "Laundry Room Two", listOf(TestData_Appliance.ELECROTROLUX_WASHER_LR2,
            TestData_Appliance.ELECTROLUX_DRYER_LR2))
    }
}