package com.laundrohem.bookingsystem.data.transfer

import java.util.*

class LaundryRoom(uuid: UUID?= null, var name: String?= null, var appliances: List<Appliance>?= null) : Bookable(uuid, Bookable.TYPE.LaundryRoom) {
}