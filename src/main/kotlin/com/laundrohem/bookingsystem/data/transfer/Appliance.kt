package com.laundrohem.bookingsystem.data.transfer

import java.util.*

class Appliance(uuid: UUID?= null, val name: String,
                val service_features : List<ServiceFeature>) : Transferrable(uuid) {
}